package com.cogtu.realtime.conf

/**
 * Created by lenovo on 2015/6/2.
 */
object ScoreBoardSystemConf {

  val SC_SPARK_MASTER = "yarn-cluster"

  val SC_SPARK_JOBNAME = "ScoreBoardCalc"

  val SC_SPARK_BATCHTIME = 5

  // zookeeper.connect=iZ257i1wyprZ:2181,iZ254hjv9e9Z:2181,iZ25dsci2umZ:2181
  val SC_KAFKA_ZK_QUORUM = "172.16.40.1:2181,172.16.40.2:2181,172.16.40.3:2181"

  val SC_KAFKA_GROUP = "randomGroup" // test-consumer-group"

  val SC_KAFKA_TOPIC = "URL"

  val SC_KAFKA_TOPIC_THREADNUM = 1

  // val SC_JSON_SPLIT_TYPE$JSONSTR = "```"

  val SC_CODING_SPLIT = ","

  // val SC_KAFKA_TOPIC_SOURCE_ATTRI_NAME = "source"

  // json串中 微博Id的字段名称
  val SC_JSON_WEIBO_COLNAME = "pageuri"
  val SC_JSON_TYPE_COLNAME = "type"

  // val SC_KAFKA_TOPIC_SOURCE_REQLOG = "req"

  // val SC_KAFKA_TOPIC_SOURCE_SPIDER = "spider"

  // 多少个batch之后从数据库中刷新一次最新的job配置信息
  val SC_STREAMING_PARAM_REFRESH_BATCHINTERVAL = 10

  val SC_REDIS_TOPN_SCOREBOARD_STACK_NAME = "ScoreBoardStack"

  val SC_REDIS_PARAM_STACKSIZE_NAME = "sc:redis:param:stacksize"

  // val SC_REDIS_TOPN_SCOREBOARD_STACK_SIZE = 50

  // reqLog日志文件保存路径
  // val SC_STREAMING_REQLOG_WRITE_PATH = "/cogtu/logsTmp"

}









