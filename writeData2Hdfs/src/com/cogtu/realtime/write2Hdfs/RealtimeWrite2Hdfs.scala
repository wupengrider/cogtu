package com.cogtu.realtime.write2Hdfs

import org.apache.spark.streaming.StreamingContext
import com.cogtu.realtime.tools.SparkUtil
import com.cogtu.realtime.conf.Write2HdfsConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.storage.StorageLevel
import java.text.SimpleDateFormat
import java.util.Date


/**
 * Created by lenovo on 2015/6/9.
 */
object RealtimeWrite2Hdfs {

  // stack的尺寸
  var stackKeepSize = 50

  def main(args: Array[String]) {
    println(" 1 write 2 hdfs streaming calc! " + args.mkString(", "))
    realtimeCalc(args)
  }

  def realtimeCalc(args: Array[String]) {
    try {
      val ssc: StreamingContext = SparkUtil.createSparkStreamingContext(args(0),
        Write2HdfsConf.SC_SPARK_JOBNAME, Write2HdfsConf.SC_SPARK_MASTER, Write2HdfsConf.SC_SPARK_BATCHTIME)
      val topicMap = args(2).split(",").map((_, Write2HdfsConf.SC_KAFKA_TOPIC_THREADNUM)).toMap
      topicMap.foreach(el => println("topic:" + el.toString()))

      val dstream = KafkaUtils.createStream(ssc, Write2HdfsConf.SC_KAFKA_ZK_QUORUM, Write2HdfsConf.SC_KAFKA_GROUP, topicMap, StorageLevel.MEMORY_AND_DISK_2)
        .map(_._2)

      var index = 0

      dstream.foreachRDD(batchRDD => {
        // 索引+1
        index += 1
        // println("batchRDD.count is " + batchRDD.count())
        // batchRDD.collect().foreach(ele => println(ele))

        if (batchRDD.count() > 0) {
          batchRDD.saveAsTextFile(Write2HdfsConf.SC_STREAMING_REQLOG_WRITE_PATH + "/reqLog_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()))
        }

        /*// 每间隔10分钟，刷新一次系统参数
        if (index % (1 * 10 / ScoreBoardSystemConf.SC_SPARK_BATCHTIME) == 0) {
          println("刷新参数：")
          index = 0
          stackKeepSize = jedisConn.get(ScoreBoardSystemConf.SC_REDIS_PARAM_STACKSIZE_NAME).toInt
          println(s"param stackKeepSize is ${stackKeepSize}")
        }*/

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
