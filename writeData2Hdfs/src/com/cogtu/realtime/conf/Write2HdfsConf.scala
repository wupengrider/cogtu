package com.cogtu.realtime.conf

/**
 * Created by lenovo on 2015/6/9.
 */
object Write2HdfsConf {
  val SC_SPARK_MASTER = "yarn-cluster"

  val SC_SPARK_JOBNAME = "Write2Hdfs"

  val SC_SPARK_BATCHTIME = 5

  // zookeeper.connect=iZ257i1wyprZ:2181,iZ254hjv9e9Z:2181,iZ25dsci2umZ:2181
  val SC_KAFKA_ZK_QUORUM = "172.16.40.1:2181,172.16.40.2:2181,172.16.40.3:2181"

  val SC_KAFKA_GROUP = "randomGroup" // test-consumer-group"

  val SC_KAFKA_TOPIC = "URL"

  val SC_KAFKA_TOPIC_THREADNUM = 1

  // val SC_JSON_SPLIT_TYPE$JSONSTR = "```"

  // 多少个batch之后从数据库中刷新一次最新的job配置信息
  val SC_STREAMING_PARAM_REFRESH_BATCHINTERVAL = 10

  val SC_STREAMING_REQLOG_WRITE_PATH = "/cogtu/logsTmp"

}
