package com.cogtu.realtime.tools

import com.cogtu.realtime.log.{ScrawlerLog, CogtuLog, ReqLog}

/**
 * Created by lenovo on 2015/6/5.
 */
object JsonParseTool {

  def jsonStr2ReqLog(logJsonStr: String): ReqLog =
    JsonTool.toBean(logJsonStr, classOf[CogtuLog]).asInstanceOf[ReqLog]

  def jsonStr2ScrawlerLog(jsonStr: String): ScrawlerLog =
    JsonTool.toBean(jsonStr, classOf[ScrawlerLog])

}
