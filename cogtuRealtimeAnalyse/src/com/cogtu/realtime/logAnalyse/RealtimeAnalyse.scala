package com.cogtu.realtime.logAnalyse

import org.apache.spark.streaming.StreamingContext
import com.cogtu.realtime.tools.{MysqlTool, JsonParseTool, SparkUtil}
import com.cogtu.realtime.conf.SystemConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{Accumulator, SparkContext}
import org.apache.spark.SparkContext._
import com.cogtu.realtime.log.{ScrawlerLog, ReqLog}
import scala.collection.JavaConversions._
import com.cogtu.realtime.dao.MysqlDao
import java.sql.Connection
import java.util.UUID
import org.json.JSONObject
import org.apache.spark.rdd.RDD
import scala.collection.mutable.ArrayBuffer

/**
 * Created by lenovo on 2015/6/5.
 */
object RealtimeAnalyse {

  /*// 请求广告数--空位数
  var requestADCountAccum: Accumulator[Int] = null
  // sc.accumulator(0, "requestADCountAccum")
  // 填充广告数
  var filledADCountAccum: Accumulator[Int] = null
  // sc.accumulator(0, "filledADCountAccum")
  // 划过广告数
  var fingeredADCountAccum: Accumulator[Int] = null
  // sc.accumulator(0, "fingeredADCountAccum")
  // 点击广告数
  var clickedADCountAccum: Accumulator[Int] = null
  // sc.accumulator(0, "clickedADCountAccum")
  // 其他reqType的记录
  var otherReqTypeCountAccum: Accumulator[Int] = null // sc.accumulator(0, "otherReqTypeCountAccum")*/

  /*// 成功下载图片个数
  val picDownloadSuccCountAccum = null // sc.accumulator(0, "picDownloadSuccCountAccum")
  // 要求下载图片个数
  val requirePicDownloadCountAccum = null // sc.accumulator(0, "requirePicDownloadCountAccum")
  // 图片下载总大小
  val downloadedPicSizeAccum = null // sc.accumulator(0, "downloadedPicSizeAccum")
  // 图片下载总时间
  val picDownloadTimeAccum = null // sc.accumulator(0, "picDownloadTimeAccum")*/

  /**
   * 将ReqLog写入HDFS，当前的batchNum。
   * 当batchNum等于BatchCount时，将当前batch的数据，以及缓存在内存中的数据写入HDFS，同时将batchNum归0。否则只将当前batch的数据缓存起来
   */
  var writeReqLog2HDFSBatchNum = 0

  /*/**
   * 积攒多少个batch的数据后写入HDFS。此数据定时从数据库中读取
   */
  var writeReqLog2HDFSBatchCount = 3*/

  /**
   * 展示 次数最多的广告 topN的N值
   */
  var fingeredAdLogTopNNum = 50

  /**
   * 填充 次数最多的广告 topN的N值
   */
  var filledAdLogTopNNum = 50

  /**
   * 浏览量最大的微博 topN的N值
   */
  var hottestWeiboIdTopNNum = 50

  // var cacheRDD: RDD[String] = null

  def main(args: Array[String]) {
    println(" 36. hello world! happy happy! LogAnalyse realtime calc system." + args.mkString(", "))
    realtimeCalc(args)
  }

  /**
   * 实时计算入口
   * @param args
   */
  def realtimeCalc(args: Array[String]) {
    val ssc: StreamingContext = SparkUtil.createSparkStreamingContext(args(0))
    // val sc: SparkContext = ssc.sparkContext

    // val topicMap = SystemConf.SC_KAFKA_TOPIC.split(",").map((_, SystemConf.SC_KAFKA_TOPIC_THREADNUM)).toMap
    val topicMap = args(2).split(",").map((_, SystemConf.SC_KAFKA_TOPIC_THREADNUM)).toMap

    val dstream = KafkaUtils.createStream(ssc, SystemConf.SC_KAFKA_ZK_QUORUM, SystemConf.SC_KAFKA_GROUP, topicMap, StorageLevel.MEMORY_AND_DISK_2)
      .map(_._2)

    dstream.foreachRDD(batchRDD => {

      /*if (batchRDD.count > 0) {
        // batchRDD.saveAsTextFile("/cogtu/logs/reqLog/testWrite_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()))
      }*/

      // 将当前的数据集RDD与之前的数据放在一起缓存起来
      /*if (cacheRDD != null) {
        cacheRDD.unpersist()
      }*/
      /*if (cacheRDD == null) {
        cacheRDD = batchRDD
      } else {
        cacheRDD.unpersist()
        cacheRDD.union(batchRDD)
      }
      cacheRDD.persist()*/
      /*// 判断是否到达缓存点
      if (writeReqLog2HDFSBatchNum == writeReqLog2HDFSBatchCount) {
        cacheRDD.saveAsTextFile(s"${SystemConf.SC_STREAMING_REQLOG_WRITE_PATH}/reqLog_${new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())}")
        writeReqLog2HDFSBatchNum = 0
        cacheRDD = null
      } else {
        writeReqLog2HDFSBatchNum += 1
      }*/

      // 将日志字符串转换成JavaObject
      // batchRDD.collect().map(jsonStr => parseJsonStr2Obj(jsonStr)).filter(null != _)
      val parsedObjRDD = batchRDD.map(jsonStr => parseJsonStr2Obj(jsonStr)).filter(null != _).repartition(args(1).toInt)
      val sc: SparkContext = parsedObjRDD.sparkContext

      val mysqlConn: Connection = MysqlTool.getConnection

      // 请求广告数
      val requestADCountAccum = sc.accumulator(0, "requestADCountAccum")
      // 填充广告数
      val filledADCountAccum = sc.accumulator(0, "filledADCountAccum")
      // 划过广告数
      val fingeredADCountAccum = sc.accumulator(0, "fingeredADCountAccum")
      // 点击广告数
      val clickedADCountAccum = sc.accumulator(0, "clickedADCountAccum")
      // 其他reqType的记录
      val otherReqTypeCountAccum = sc.accumulator(0, "otherReqTypeCountAccum")
      // 展示的广告的price和
      val fingeredADPriceSumAccum = sc.accumulator(0.0, "fingeredADPriceSumAccum")

      /*parsedObjRDD.collect().foreach(obj => if (obj.isInstanceOf[ReqLog]) {
        println("reqLog: reqs size is " + obj.asInstanceOf[ReqLog].getReqs.size())
      } else if (obj.isInstanceOf[ScrawlerLog]) {
        println("scrawlerLog: pics size is " + obj.asInstanceOf[ScrawlerLog].getPics.size())
      })*/

      // println("parsedObjRDD.count=" + parsedObjRDD.count())

      // 逐条数据的对所需计算的指标进行累加
      parsedObjRDD.foreach(obj => {
        // 计算core指标
        if (obj.isInstanceOf[ReqLog]) {
          // val reqLog = obj.asInstanceOf[ReqLog]
          calcReqLog(obj, requestADCountAccum, filledADCountAccum, fingeredADCountAccum, clickedADCountAccum, otherReqTypeCountAccum, fingeredADPriceSumAccum)
        } /*else if (obj.isInstanceOf[ScrawlerLog]) {
        }*/
      })

      // 广告推送日志
      val reqLogRDD = parsedObjRDD.filter(obj => obj.isInstanceOf[ReqLog]).map(obj => obj.asInstanceOf[ReqLog])

      // QPS统计
      val qps = reqLogRDD.count
      // val qps = parsedObjRDD.filter(obj => obj.isInstanceOf[ReqLog]).count

      val scrawlerLogRDD = parsedObjRDD.filter(obj => obj.isInstanceOf[ScrawlerLog]).map(obj => obj.asInstanceOf[ScrawlerLog])
      // ------------------------ 计算图片下载爬虫的相关指标 start ------------------------
      // 转换成按照pid和hostIp为key的键值对，然后分组。保证数据被均匀的分布到多个partition中。主机10台左右，每台主机pid进程几十个左右，如此可以保证至少有几百个group
      val downloadPicHostDetailRDD = scrawlerLogRDD.map(scrawlerLog => {
        (scrawlerLog.getPid + SystemConf.SC_CODING_SPLIT + scrawlerLog.getHostIp, scrawlerLog)
      }).groupByKey()
        // 统计每台主机上的每个进程pid上下载的图片信息
        .map {
        case (pid$hostIp, scrawlerLogIter) => {
          var downloadPicCount = 0l // 实际下载图片数
          var requiredPicDownloadCount = 0l // 请求下载图片数
          var picSizeSum = 0l // 图片总大小
          var picDownloadTimeSum = 0l // 图片下载总时间
          var mqSendTimeSum = 0l // MQ发送时间

          val iter = scrawlerLogIter.toIterator
          while (iter.hasNext) {
            val scrawlerLog = iter.next()
            for (picInfo <- scrawlerLog.getPics) {
              if (picInfo.isPicDownloadSucc) {
                downloadPicCount += 1
              }
              picSizeSum += picInfo.getSize
              picDownloadTimeSum += picInfo.getUseTime
              mqSendTimeSum += scrawlerLog.getMqSendTime
            }
            requiredPicDownloadCount += scrawlerLog.getPics.size()
          }
          // 转换成(hostIp, pid进程统计信息)的格式。每台host上大概会跑几十个pid进程
          // val (_, hostIp) = pid$hostIp.split(SystemConf.SC_CODING_SPLIT)
          // (hostIp,
          (pid$hostIp.split(SystemConf.SC_CODING_SPLIT)(1),
            List(downloadPicCount, requiredPicDownloadCount, picSizeSum, picDownloadTimeSum, scrawlerLogIter.size.toLong, mqSendTimeSum))
        }
      }
        // 将之前按照pid进程及host分组统计之后的结果，再按照host进行分组，然后合计每个host的图片信息
        .groupByKey().map {
        case (hostIp, pidStatisticsIt) => {
          var downloadPicCount = 0l // 实际下载图片数
          var requiredPicDownloadCount = 0l // 请求下载图片数
          var picSizeSum = 0l // 图片总大小
          var picDownloadTimeSum = 0l // 图片下载总时间
          var handleRequestSum = 0l // 处理请求数
          var mqSendTimeSum = 0l // MQ发送时间
          for (statisticslist <- pidStatisticsIt) {
            downloadPicCount += statisticslist(0)
            requiredPicDownloadCount += statisticslist(1)
            picSizeSum += statisticslist(2)
            picDownloadTimeSum += statisticslist(3)
            handleRequestSum += statisticslist(4)
            mqSendTimeSum += statisticslist(5)
          }
          (hostIp, downloadPicCount, requiredPicDownloadCount, picSizeSum, picDownloadTimeSum, handleRequestSum, mqSendTimeSum)
        }
      }
      // ------------------------ 计算图片下载爬虫的相关指标 end ------------------------

      // ------------------------ 将爬虫计算的相关信息写入Mysql start ------------------------
      // 此时collect之后的数据量为主机个数--大概10条左右
      val hostDetailAry = downloadPicHostDetailRDD.collect()
      val batchNo = UUID.randomUUID.toString
      hostDetailAry.foreach(result => {
        val (hostIp, dloadPicCount, reqDloadPicCount, picSizeSum, dloadConsumTime, handleRequestSum, mqSendTimeSum) = result

        // 平均下载速度
        val avgDloadSpeed = if (dloadConsumTime == 0l) {
          0.0
        } else {
          picSizeSum.toDouble / dloadConsumTime
        }
        // 下载成功率
        val dloadSuccRate = if (reqDloadPicCount == 0l) {
          0.0
        } else {
          dloadPicCount / reqDloadPicCount.toDouble
        }
        MysqlDao.insertScrawlerLogHostResult(mysqlConn, SystemConf.SC_SPARK_BATCHTIME.toString, batchNo, hostIp, dloadPicCount,
          reqDloadPicCount, picSizeSum, dloadConsumTime, avgDloadSpeed, dloadSuccRate, handleRequestSum, mqSendTimeSum)
        println(s"主机：${result._1}\n处理请求数：${result._6}\n实际下载图片数：${result._2}\n请求下载图片数：${result._3}\n图片总大小：${result._4} kb\n总消耗时间：${result._5} ms\n平均下载速度：$avgDloadSpeed kb/ms\n下载成功率：$dloadSuccRate\nMQ发送时间求和：$mqSendTimeSum\n---------------------------------------")
      })

      try {
        // 爬虫日志总量
        val scrawlerLogCount = scrawlerLogRDD.count.toDouble
        // 代理不足量
        val agentLackTimes = scrawlerLogRDD.filter(scrawlerLog => scrawlerLog.getErrorCode == "111").count.toDouble
        // 代理不足率
        val agentLackRate = if (0.0 == scrawlerLogCount) {
          0.0
        } else {
          agentLackTimes / scrawlerLogCount
        }
        // 任务信息不完整数
        val taskInfoUnIntegrityTimes = scrawlerLogRDD.filter(scrawlerLog => scrawlerLog.getWeibo.getIsDownload == 1).count.toDouble
        // 任务信息不完整度
        val taskInfoUnIntegrityRate = if (scrawlerLogCount - agentLackTimes <= 0) {
          0.0
        } else {
          taskInfoUnIntegrityTimes / (scrawlerLogCount - agentLackTimes)
        }
        // 将分析结果写入Mysql
        if (scrawlerLogCount > 0) {
          // 只有当前从kafka中读取到数据后，才会向mysql中写入数据
          MysqlDao.insertScrawlerLogResult(mysqlConn, SystemConf.SC_SPARK_BATCHTIME.toString, batchNo, scrawlerLogCount.toLong,
            agentLackTimes.toLong, agentLackRate, taskInfoUnIntegrityTimes.toLong, taskInfoUnIntegrityRate)
          println("batch no is: " + batchNo)
        }
        println(s"---------------------------------------\n处理请求总数：$scrawlerLogCount\n代理不足无法启动下载的数量：$agentLackTimes\n代理不足占比：${agentLackRate}\n任务信息不完整数：$taskInfoUnIntegrityTimes\n任务信息不完整占比：${taskInfoUnIntegrityRate}")

        // ------------------------ 将爬虫计算的相关信息写入Mysql end ------------------------

        // 统计广告推送模块相关指标
        // 计算填充次数最多的广告 取topN
        val filledReqLogTopNJsonStr = calcAdLogTopN(reqLogRDD, filterFilledReqLogRecords)
        // 计算展示次数最多的广告 取topN
        val fingeredReqLogTopNJsonStr = calcAdLogTopN(reqLogRDD, filterFingeredReqLogRecords)
        // 计算当前时间段内最热门的微博Id
        val hottestWeiboIdTopNJsonStr = calcHottestWeiboIdTopN(reqLogRDD)
        // 将核心指标数据写入mysql
        if (qps > 0) {
          writeReqLogInfo2Mysql(mysqlConn, qps, requestADCountAccum.value, filledADCountAccum.value, fingeredADCountAccum.value,
            clickedADCountAccum.value, fingeredADPriceSumAccum.value, filledReqLogTopNJsonStr, fingeredReqLogTopNJsonStr, hottestWeiboIdTopNJsonStr)
        }
        /*val reqLogRDD = parsedObjRDD.filter(obj => obj.isInstanceOf[ReqLog]).map(obj => obj.asInstanceOf[ReqLog])
        reqLogRDD.flatMap(reqLog => {
          // 将adLog转换成(广告相关主键, 1)的格式
          reqLog.getReqs.map(adLog => (adLog.getAdId + adLog.getAdvId + adLog.getAdProjectId + adLog.getAdCampaignId, 1))
        })
          .groupByKey()*/
      } catch {
        case e: Exception => e.printStackTrace()
      } finally {
        MysqlTool.closeConnection(mysqlConn)
        println("close mysql conn")
      }

      println("------------------------------------------------------------------------------------------------------------------------------")
    })

    dstream.print()
    ssc.start()
    ssc.awaitTermination()
  }

  /**
   * 计算当前时间段内 浏览次数最多的weiboid
   * @param reqLogRDD
   * @return
   */
  def calcHottestWeiboIdTopN(reqLogRDD: RDD[ReqLog]): String = {
    val jsonBuff = new StringBuffer()
    // 计算取出当前时间段内最火的微博
    val hottestWeibos = reqLogRDD.map(reqLog => (reqLog.getPageUri, 1)).groupByKey().map {
      case (weiboId, reqLogIter) => (weiboId, reqLogIter.size)
    }
      .sortBy {
      case (weiboId, viewCount) => viewCount
    }.take(hottestWeiboIdTopNNum)

    // 拼接json串
    hottestWeibos.foreach {
      case (weiboId, viewCount) => {
        // val ids = adLogIds.split(",")
        if (jsonBuff.toString != "") {
          jsonBuff.append(",")
        }
        else {
          jsonBuff.append("{\"weibos\":[")
        }
        jsonBuff.append("{\"weiboid\":\"").append(weiboId).append("\",\"count\":").append(viewCount).append("}")
      }
    }
    jsonBuff.append("]}")
    println("hottest weiboid TopN json is:" + jsonBuff.toString)
    jsonBuff.toString
  }

  /**
   * 计算广告次数TopN的广告信息
   * @param reqLogRDD
   * @param reqLogFilter reqLog过滤器
   */
  def calcAdLogTopN(reqLogRDD: RDD[ReqLog], reqLogFilter: (ReqLog) => Boolean): String = {
    // val topNJsonStr = new ArrayBuffer[String]()
    // topNJsonStr.append("{\"topNAdLogs\":[")
    val buff = new StringBuffer()
    reqLogRDD.filter(reqLog => {
      reqLogFilter(reqLog)
    }).flatMap(reqLog => {
      reqLog.getReqs.map(adLog =>
        if (adLog.isAdLogLegal) {
          (s"${adLog.getCreativeId},${adLog.getAdvertisersId},${adLog.getProjectId},${adLog.getCampaignId}", 1)
        }
        else {
          null
        }).filter(el => el != null)
    }).reduceByKey(_ + _).sortBy(_._2).take(fingeredAdLogTopNNum).sortBy(_._2).foreach {
      case (adLogIds, count) => {
        val ids = adLogIds.split(",")
        // if (!topNJsonStr.isEmpty) {
        if (buff.toString != "") {
          buff.append(",")
        }
        else {
          buff.append("{\"topNAdLogs\":[")
          // topNJsonStr append "{\"topNAdLogs\":["
        }
        buff.append("{\"adId\":").append(ids(0)).append(",\"advId\":").append(ids(1)).append(",\"adProjectId\":").append(ids(2)).append(",\"adCampaignId\":").append(ids(3)).append(",\"count\":").append(count).append("}")
        // topNJsonStr append ("{\"adId\":" + ids(0) + ",\"advId\":" + ids(1) + ",\"adProjectId\":" + ids(2) + ",\"adCampaignId\":" + ids(3) + ",\"count\":" + count + "}")
      }
    }
    buff.append("]}")
    println("TopN json is:" + buff.toString)
    buff.toString
    // topNJsonStr append "]}"
    // println("topNJsonStr.toString=" + topNJsonStr.toString())
    // topNJsonStr.mkString(",")

    /*parsedObjRDD.filter(obj => {
      reqLogFilter
    }).flatMap(obj => {
      val reqLog = obj.asInstanceOf[ReqLog]
      reqLog.getReqs.map(adLog => if (adLog.isAdLogLegal) {
        (s"${adLog.getAdId},${adLog.getAdvId},${adLog.getAdProjectId},${adLog.getAdCampaignId}", 1)
      } else {
        null
      }).filter(_ != null)
    }).reduceByKey(_ + _).sortBy(_._2).take(fingeredAdLogTopNNum).foreach {
      case (adLogIds, count) => {
        // 将广告日志信息写入数据库
      }
    }*/

  }

  def filterFilledReqLogRecords(reqLog: ReqLog): Boolean = {
    reqLog.getReqType == 1
    /*if (obj.isInstanceOf[ReqLog]) {
      val reqLog = obj.asInstanceOf[ReqLog]
      reqLog.getReqType == 1
    } else {
      false
    }*/
  }

  def filterFingeredReqLogRecords(reqLog: ReqLog): Boolean = {
    reqLog.getReqType == 2
    /*if (obj.isInstanceOf[ReqLog]) {
      val reqLog = obj.asInstanceOf[ReqLog]
      reqLog.getReqType == 2
    } else {
      false
    }*/
  }

  /**
   * 将广告日志分析结果写入mysql
   * @param conn
   * @param qps
   * @param requestADCount
   * @param filledADCount
   * @param fingeredADCount
   * @param clickedADCount
   */
  def writeReqLogInfo2Mysql(conn: Connection, qps: Long, requestADCount: Int, filledADCount: Int, fingeredADCount: Int,
                            clickedADCount: Int, fingeredADPriceSum: Double, filledReqLogTopNJsonStr: String, fingeredReqLogTopNJsonStr: String, hottestWeiboIdTopNJsonStr: String) {
    println("create mysql connection.")
    // val conn = MysqlTool.getConnection
    try {
      // 填充率
      val fillRate = if (requestADCount != 0) {
        filledADCount / requestADCount.toDouble
      } else {
        0.0
      }
      // 渲染率
      val renderRate = if (filledADCount != 0) {
        fingeredADCount / filledADCount.toDouble
      } else {
        0.0
      }
      // 点击率
      val clickRate = if (fingeredADCount != 0) {
        clickedADCount / fingeredADCount.toDouble
      } else {
        0.0
      }
      MysqlDao.insertReqLogAnalyseResult(conn, SystemConf.SC_SPARK_BATCHTIME.toString, fillRate, renderRate, clickRate, 0, 0, filledReqLogTopNJsonStr, fingeredReqLogTopNJsonStr, hottestWeiboIdTopNJsonStr)

      println("QPS：" + qps + ",\n请求广告数：" + requestADCount + ",\n填充广告数：" + filledADCount
        + ",\n展示广告数：" + fingeredADCount + ",\n点击广告数：" + clickedADCount)
      println(s"填充率：$fillRate\n渲染率：$renderRate\n点击率：$clickRate\n展示广告总价格：$fingeredADPriceSum")
      println(s"填充广告TopN：$filledReqLogTopNJsonStr\n展示广告TopN：$fingeredReqLogTopNJsonStr\n浏览次数最多微博TopN：$hottestWeiboIdTopNJsonStr")
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      // 在foreachRDD开始时创建conn，结束时关闭conn
      // MysqlTool.closeConnection(conn)
      // println("close mysql connection.")
      println("------------------------------------------------------------------")
    }
  }

  /**
   * 将json字符串转换成java对象
   * @param jsonStr 格式为 type + SystemConf.SC_JSON_SPLIT_TYPE$JSONSTR + jsonStr
   * @return
   */
  def parseJsonStr2Obj(jsonStr: String): Object = {
    // val ary = jsonStr.split(SystemConf.SC_JSON_SPLIT_TYPE$JSONSTR)
    // if (record == null || "" == record || record.split(SystemConf.SC_JSON_SPLIT_TYPE$JSONSTR).size < 2) {
    if (jsonStr == null || "" == jsonStr) {
      null
    } else {
      // val Array(strType, jsonStr) = record.split(SystemConf.SC_JSON_SPLIT_TYPE$JSONSTR)
      val jsonObject = new JSONObject(jsonStr)
      var obj: Object = null
      // core data
      // if (strType == "0") {
      println("parseJsonStr2Obj -----> source=" + jsonObject.getString(SystemConf.SC_KAFKA_TOPIC_SOURCE_ATTRI_NAME))
      if (jsonObject.getString(SystemConf.SC_KAFKA_TOPIC_SOURCE_ATTRI_NAME) == SystemConf.SC_KAFKA_TOPIC_SOURCE_REQLOG) {
        obj = JsonParseTool.jsonStr2ReqLog(jsonStr)
        println("reqLog")
      }
      // spider 爬虫
      else if (jsonObject.getString(SystemConf.SC_KAFKA_TOPIC_SOURCE_ATTRI_NAME) == SystemConf.SC_KAFKA_TOPIC_SOURCE_SPIDER) {
        obj = JsonParseTool.jsonStr2ScrawlerLog(jsonStr)
        println("scrawlerLog")
      }
      obj
    }
  }

  /**
   * 处理coreData的日志
   * @param reqLogObj
   */
  def calcReqLog(reqLogObj: Object, requestADCountAccum: Accumulator[Int], filledADCountAccum: Accumulator[Int],
                 fingeredADCountAccum: Accumulator[Int], clickedADCountAccum: Accumulator[Int], otherReqTypeCountAccum: Accumulator[Int], fingeredADPriceSumAccum: Accumulator[Double]) {
    val reqLog: ReqLog = reqLogObj.asInstanceOf[ReqLog]
    if (null != reqLog) {
      val reqs = reqLog.getReqs
      // 广告请求
      if (reqLog.getReqType == 1) {
        // reqs个数为0的时候也算1个
        val requestCount = if (reqs.size() == 0) {
          1
        } else {
          reqs.size()
        }
        // 请求广告数
        requestADCountAccum += requestCount

        // 填充广告数
        val filledAdCount = reqs.filter(adLog => adLog.isAdLogLegal).size
        // println("id: " + reqLog.getTimestamp1 + ", 填充广告数=" + filledAdCount)
        filledADCountAccum += filledAdCount // 填充广告数
      }
      // 广告展示
      else if (reqLog.getReqType == 2) {
        fingeredADCountAccum += (if (reqs != null) {
          reqs.size()
        } else {
          0
        })
        // 所展示的广告的price Sum
        var adPriceSum = 0.0
        for (reqLog <- reqs) {
          adPriceSum += reqLog.getPrice
        }
        fingeredADPriceSumAccum += adPriceSum
      }
      // 广告点击
      else if (reqLog.getReqType == 3) {
        clickedADCountAccum += (if (reqs != null) {
          reqs.size()
        } else {
          0
        })
      } else {
        otherReqTypeCountAccum += 1
      }
    }
  }

  /*/**
   * 恢复累加器为0
   * @param sc sparkContext
   */
  def resetAccumulators(sc: SparkContext) {
    // 请求广告数--空位数
    requestADCountAccum = resetAccumulator(requestADCountAccum, "requestADCountAccum", sc)
    // 填充广告数
    filledADCountAccum = resetAccumulator(filledADCountAccum, "filledADCountAccum", sc)
    // filledADCountAccum.setValue(0)
    // 划过广告数
    fingeredADCountAccum = resetAccumulator(fingeredADCountAccum, "fingeredADCountAccum", sc)
    // fingeredADCountAccum.setValue(0)
    // 点击广告数
    clickedADCountAccum = resetAccumulator(clickedADCountAccum, "clickedADCountAccum", sc)
    // clickedADCountAccum.setValue(0)
    // 其他reqType的记录
    otherReqTypeCountAccum = resetAccumulator(otherReqTypeCountAccum, "otherReqTypeCountAccum", sc)
    // otherReqTypeCountAccum.setValue(0)
  }*/

  /*/**
   * 重置累加器
   * @param myAccumulator 需要重置的累加器
   * @param accumulatorName 累加器的名称
   * @return 重置后的累加器
   */
  def resetAccumulator(myAccumulator: Accumulator[Int], accumulatorName: String, sc: SparkContext): Accumulator[Int] = {
    val resettedAccum = if (null == myAccumulator) {
      sc.accumulator(0, accumulatorName)
    } else {
      myAccumulator.setValue(0)
      myAccumulator
    }
    println("resetAccumulator: resettedAccum: " + resettedAccum)
    resettedAccum
  }*/

  /**
   * 处理爬虫的日志
   * @param scrawlerLogObj
   */
  def calcScrawlerLog(scrawlerLogObj: Object) {
  }

}
















