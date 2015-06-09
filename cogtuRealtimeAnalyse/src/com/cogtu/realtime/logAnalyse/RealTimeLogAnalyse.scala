package com.cogtu.realtime.logAnalyse

import org.apache.spark.streaming.StreamingContext
import scala.collection.JavaConversions._
import org.apache.spark.SparkContext._
import com.cogtu.realtime.tools.{JsonParseTool, MysqlTool, SparkUtil}
import org.apache.spark.streaming.kafka.KafkaUtils
import com.cogtu.realtime.conf.SystemConf
import org.apache.spark.SparkContext
import java.util.{UUID, Date}
import org.apache.spark.storage.StorageLevel
import java.sql.{PreparedStatement, Connection, Timestamp}
import com.cogtu.realtime.dao.MysqlDao

/**
 * Created by lenovo on 2015/6/2.
 */
object RealTimeLogAnalyse {

  var repartitionNum: Int = 8

  def main(args: Array[String]) {
    println("hello world! kafka streaming! 32 " + SystemConf.SC_KAFKA_GROUP)
    realtimeCalcEntrance(args)
    // test2
  }

  def initSystemProperties(args: Array[String]) {
    System.setProperty("spark.default.parallelism", args(0))
    repartitionNum = args(1).toInt
  }

  def realtimeCalcEntrance(args: Array[String]) {
    val ssc: StreamingContext = SparkUtil.createSparkStreamingContext(args(0))
    val topicMap = SystemConf.SC_KAFKA_TOPIC.split(",").map((_, SystemConf.SC_KAFKA_TOPIC_THREADNUM)).toMap
    // topicMap.toList.foreach(el => println("topic: " + el._1 + ", " + el._2))

    val dstream = KafkaUtils.createStream(ssc, SystemConf.SC_KAFKA_ZK_QUORUM, SystemConf.SC_KAFKA_GROUP, topicMap, StorageLevel.MEMORY_AND_DISK_2)
      .map(_._2)

    dstream.foreachRDD(batchRDD => {
      // spark context
      val sc: SparkContext = batchRDD.sparkContext
      // 将原始字符串转换成ReqLog对象的形式
      val reqLogRDD = batchRDD.map(JsonParseTool.jsonStr2ReqLog(_)).repartition(repartitionNum)
      // QPS统计
      val qps = batchRDD.count
      // 请求广告数--空位数
      val requestADCountAccum = sc.accumulator(0, "requestADCountAccum")
      // 填充广告数
      val filledADCountAccum = sc.accumulator(0, "filledADCountAccum")
      // 划过广告数
      val fingeredADCountAccum = sc.accumulator(0, "fingeredADCountAccum")
      // 点击广告数
      val clickedADCountAccum = sc.accumulator(0, "clickedADCountAccum")
      // 其他reqType的记录
      val otherReqTypeCountAccum = sc.accumulator(0, "otherReqTypeCountAccum")

      /*val v = reqLogRDD.groupBy(elem => {
        elem.getReqType
      })
      println("v.count=" + v.count())*/

      reqLogRDD.foreach(reqLog => {
        if (null != reqLog) {

          /*println("------------------------------------")
          println("id: " + reqLog.getTimestamp1 + ", type=" + reqLog.getReqType + ", reqs.size=" + reqLog.getReqs.size())*/

          val reqs = reqLog.getReqs
          // 广告请求
          if (reqLog.getReqType == 1) {
            // reqs个数为0的时候也算1个
            val requestCount = if (reqs.size() == 0) {
              1
            } else {
              reqs.size()
            }
            requestADCountAccum += requestCount

            // 填充广告数
            val filledAdCount = reqs.filter(adLog => adLog.isAdLogLegal).size
            // println("id: " + reqLog.getTimestamp1 + ", 填充广告数=" + filledAdCount)
            filledADCountAccum += filledAdCount // 填充广告数
          }
          // 广告展示
          else if (reqLog.getReqType == 2) {
            fingeredADCountAccum += reqs.size()
          }
          // 广告点击
          else if (reqLog.getReqType == 3) {
            clickedADCountAccum += reqs.size()
          } else {
            otherReqTypeCountAccum += 1
          }
        }
      })

      println("create mysql connection.")
      val conn = MysqlTool.getConnection
      try {
        // 填充率
        val fillRate = filledADCountAccum.value / requestADCountAccum.value.toDouble
        // 渲染率
        val renderRate = fingeredADCountAccum.value / filledADCountAccum.value.toDouble
        // 点击率
        val clickRate = clickedADCountAccum.value.toDouble / fingeredADCountAccum.value
        // 将计算结果写入mysql
        // MysqlDao.insertReqLogAnalyseResult(conn, SystemConf.SC_SPARK_BATCHTIME.toString, fillRate, renderRate, clickRate, 0, 0)

        println("QPS为：" + qps + ",\n请求广告数为：" + requestADCountAccum.value + ",\n填充广告数为：" + filledADCountAccum.value
          + ",\n展示广告数为：" + fingeredADCountAccum.value + ",\n点击广告数为：" + clickedADCountAccum.value)
        println(s"填充率为：$fillRate\n渲染率为：$renderRate\n点击率为：$clickRate")
      } catch {
        case e: Exception => e.printStackTrace()
      } finally {
        MysqlTool.closeConnection(conn)
        println("close mysql connection.")
      }

      println("rdd count is " + batchRDD.count() + ", date is " + new Date().toString)
      println("------------------------------------------------------------------------------------------------------------------")
    })

    // dstream.print()
    ssc.start()
    ssc.awaitTermination()
  }

}























