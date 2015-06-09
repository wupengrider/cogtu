package com.cogtu.realtime.scoreboard

import org.apache.spark.streaming.StreamingContext
import com.cogtu.realtime.tools.{SingleJedis, SparkUtil}
import com.cogtu.realtime.conf.{PublicConf, ScoreBoardSystemConf}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.storage.StorageLevel
import org.json.JSONObject
import org.apache.spark.SparkContext._
import redis.clients.jedis.Jedis


/**
 * Created by lenovo on 2015/6/9.
 */
object ScoreBoardCalc {

  // stack的尺寸
  var stackKeepSize = 50

  def main(args: Array[String]) {
    println(" 10 score board streaming calc! " + args.mkString(", "))
    realtimeCalc(args)
  }

  def realtimeCalc(args: Array[String]) {
    try {
      val ssc: StreamingContext = SparkUtil.createSparkStreamingContext(args(0),
        ScoreBoardSystemConf.SC_SPARK_JOBNAME, ScoreBoardSystemConf.SC_SPARK_MASTER, ScoreBoardSystemConf.SC_SPARK_BATCHTIME)
      val topicMap = args(2).split(",").map((_, ScoreBoardSystemConf.SC_KAFKA_TOPIC_THREADNUM)).toMap
      topicMap.foreach(el => println("topic:" + el.toString()))

      val jedisConn = SingleJedis.getInstance()
      // println(s"jedisConn OK. get param stackKeepSize ${jedisConn.get(ScoreBoardSystemConf.SC_REDIS_PARAM_STACKSIZE_NAME).toInt}")

      val dstream = KafkaUtils.createStream(ssc, ScoreBoardSystemConf.SC_KAFKA_ZK_QUORUM, ScoreBoardSystemConf.SC_KAFKA_GROUP, topicMap, StorageLevel.MEMORY_AND_DISK_2)
        .map(_._2)

      // println("11111")

      var index = 0

      dstream.foreachRDD(batchRDD => {

        // 索引+1
        index += 1

        // println("batchRDD.count is " + batchRDD.count())
        // batchRDD.collect().foreach(ele => println(ele))

        batchRDD.map(jsonStr => {
          var myJsonObject: JSONObject = null
          try {
            myJsonObject = new JSONObject(jsonStr)
          } catch {
            case e: Exception => println(e.getMessage)
          }
          if (null != myJsonObject) {
            (myJsonObject.getString(ScoreBoardSystemConf.SC_JSON_WEIBO_COLNAME)
              + myJsonObject.getString(ScoreBoardSystemConf.SC_JSON_TYPE_COLNAME), jsonStr)
          } else {
            (null, null)
          }
        }).filter(el => el._1 != null && el._2 != null)
          .groupByKey
          .foreachPartition(iter => {
          // val jedis = SingleJedis.getInstance()
          val jedis = new Jedis(PublicConf.SC_REDIS_IP, PublicConf.SC_REDIS_PORT)
          jedis.ltrim(ScoreBoardSystemConf.SC_REDIS_TOPN_SCOREBOARD_STACK_NAME, 0l, stackKeepSize.toLong)
          val iterator = iter.toIterator
          while (iterator.hasNext) {
            val (_, jsonStrIter) = iterator.next()
            jedis.lpush(ScoreBoardSystemConf.SC_REDIS_TOPN_SCOREBOARD_STACK_NAME, jsonStrIter.head)
          }
        })

        // 每间隔10分钟，刷新一次系统参数
        if (index % (1 * 10 / ScoreBoardSystemConf.SC_SPARK_BATCHTIME) == 0) {
          println("刷新参数：")
          index = 0
          stackKeepSize = jedisConn.get(ScoreBoardSystemConf.SC_REDIS_PARAM_STACKSIZE_NAME).toInt
          println(s"param stackKeepSize is ${stackKeepSize}")
        }

      })

      dstream.print()
      ssc.start()
      ssc.awaitTermination()
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {

    }
  }


}
