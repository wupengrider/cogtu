package com.cogtu.realtime.tools

import org.apache.spark.{SparkConf, SparkContext}
import com.cogtu.realtime.conf.SystemConf
import org.apache.spark.streaming.{StreamingContext, Seconds}

/**
 * Created by lenovo on 2015/6/2.
 */
object SparkUtil {

  def createSparkStreamingContext(parallelismNum: String): StreamingContext = {
    // System.setProperty("auto.offset.reset", "smallest")
    System.setProperty("spark.default.parallelism", parallelismNum)
    val sparkConf = new SparkConf().setAppName(SystemConf.SC_SPARK_JOBNAME).setMaster(SystemConf.SC_SPARK_MASTER)
    new StreamingContext(sparkConf, Seconds(SystemConf.SC_SPARK_BATCHTIME))
  }

}
