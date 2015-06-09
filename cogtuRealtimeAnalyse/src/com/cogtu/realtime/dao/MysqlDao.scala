package com.cogtu.realtime.dao

import java.sql.{Timestamp, PreparedStatement, Connection}
import java.util.{Date, UUID}

/**
 * Created by lenovo on 2015/6/5.
 */
object MysqlDao {

  def insertScrawlerLogResult(conn: Connection, timeInterval: String, batchNo: String, dloadReqCount: Long, agentLackCount: Long, agentLackRate: Double, taskInfoIncompleteCount: Long, taskInfoIncompleteRate: Double) {
    // val tool: MysqlTool = new MysqlTool
    // val conn: Connection = tool.getConnection

    try {
      val insertSql: String = "insert into rt_picdownload_detail(ID,TIME_INTERVAL,TIMESTAMP,BATCH_NO,DLOAD_REQ_COUNT,AGENT_LACK_COUNT,AGENT_LACK_RATE,TASK_INFO_INCOMPLETE_COUNT,TASK_INFO_INCOMPLETE_RATE) values(?,?,?,?,?,?,?,?,?)"
      val pstatement: PreparedStatement = conn.prepareStatement(insertSql)
      pstatement.setString(1, UUID.randomUUID.toString)
      pstatement.setString(2, timeInterval)
      pstatement.setTimestamp(3, new Timestamp(new Date().getTime))
      pstatement.setString(4, batchNo)
      pstatement.setLong(5, dloadReqCount)
      pstatement.setLong(6, agentLackCount)
      pstatement.setDouble(7, agentLackRate)
      pstatement.setLong(8, taskInfoIncompleteCount)
      pstatement.setDouble(9, taskInfoIncompleteRate)
      pstatement.executeUpdate
    }
    catch {
      case e: Exception => {
        e.printStackTrace
      }
    }
    finally {
      /*try {
        conn.close
      }
      catch {
        case e: Exception => {
          e.printStackTrace
        }
      }*/
    }
  }

  /**
   * 将host的下载日志分析记录写到mysql
   * @param conn
   * @param timeInterval
   * @param batchNo
   * @param hostIp
   * @param dloadPicCount
   * @param reqDloadPicCount
   * @param picSizeSum
   * @param dloadConsumTime
   * @param dloadSpeed
   */
  def insertScrawlerLogHostResult(conn: Connection, timeInterval: String, batchNo: String, hostIp: String, dloadPicCount: Long,
                                  reqDloadPicCount: Long, picSizeSum: Long, dloadConsumTime: Long, dloadSpeed: Double, dloadSuccRate: Double, handleRequestSum: Long, mqSendTimeSum: Long) {
    // val tool: MysqlTool = new MysqlTool
    // val conn: Connection = tool.getConnection

    try {
      val insertSql: String = "insert into rt_picdownload_hostdetail(ID,TIME_INTERVAL,TIMESTAMP,BATCH_NO,HOST_IP,DLOAD_PIC_COUNT,REQ_DLOAD_PIC_COUNT,PIC_SIZE_SUM,DLOAD_CONSUM_TIME,DLOAD_SPEED,DLOAD_SUCC_RATE,HANDLE_REQ_SUM,MQ_SENDTIME_SUM) values(?,?,?,?,?,?,?,?,?,?,?,?,?)"
      val pstatement: PreparedStatement = conn.prepareStatement(insertSql)
      pstatement.setString(1, UUID.randomUUID.toString)
      pstatement.setString(2, timeInterval)
      pstatement.setTimestamp(3, new Timestamp(new Date().getTime))
      pstatement.setString(4, batchNo)
      pstatement.setString(5, hostIp)
      pstatement.setLong(6, dloadPicCount)
      pstatement.setLong(7, reqDloadPicCount)
      pstatement.setLong(8, picSizeSum)
      pstatement.setLong(9, dloadConsumTime)
      pstatement.setDouble(10, dloadSpeed)
      pstatement.setDouble(11, dloadSuccRate)
      pstatement.setLong(12, handleRequestSum)
      pstatement.setLong(13, mqSendTimeSum)
      pstatement.executeUpdate
    }
    catch {
      case e: Exception => {
        e.printStackTrace
      }
    }
    finally {
      /*try {
        conn.close
      }
      catch {
        case e: Exception => {
          e.printStackTrace
        }
      }*/
    }
  }

  /**
   * 将广告请求信息写入mysql
   * @param conn
   * @param timeInterval
   * @param fillRate
   * @param renderRate
   * @param clickRate
   * @param crawlerErrTime
   * @param crawlerSuccTime
   */
  def insertReqLogAnalyseResult(conn: Connection, timeInterval: String, fillRate: Double, renderRate: Double, clickRate: Double,
                                crawlerErrTime: Int, crawlerSuccTime: Int, filledReqLogTopNJsonStr: String, fingeredReqLogTopNJsonStr: String, hottestWeiboIdTopNJsonStr: String, qps: Long) {
    // val tool: MysqlTool = new MysqlTool
    // val conn: Connection = tool.getConnection

    try {
      val insertSql: String = "insert into rt_ad_push(ID,TIME_INTERVAL,TIMESTAMP,FILL_RATE,RENDER_RATE,CLICK_RATE,CRAWLER_ERR_TIME,CRAWLER_SUCC_TIME,FILLED_TOPN_AD,FINGERED_TOPN_AD,HOTTEST_WEIBO_TOPN, qps) values(?,?,?,?,?,?,?,?,?,?,?,?)"
      val pstatement: PreparedStatement = conn.prepareStatement(insertSql)
      pstatement.setString(1, UUID.randomUUID.toString)
      pstatement.setString(2, timeInterval)
      pstatement.setTimestamp(3, new Timestamp(new Date().getTime))
      pstatement.setDouble(4, fillRate)
      pstatement.setDouble(5, renderRate)
      pstatement.setDouble(6, clickRate)
      pstatement.setInt(7, crawlerErrTime)
      pstatement.setInt(8, crawlerSuccTime)
      pstatement.setString(9, filledReqLogTopNJsonStr)
      pstatement.setString(10, fingeredReqLogTopNJsonStr)
      pstatement.setString(11, hottestWeiboIdTopNJsonStr)
      pstatement.setLong(12, qps)
      pstatement.executeUpdate
    }
    catch {
      case e: Exception => {
        e.printStackTrace
      }
    }
    finally {
      /*try {
        conn.close
      }
      catch {
        case e: Exception => {
          e.printStackTrace
        }
      }*/
    }
  }

}
