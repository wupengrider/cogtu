package com.cogtu.realtime.tools

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{StreamingContext, Seconds}

/**
 * Created by lenovo on 2015/6/2.
 */
object SparkUtil {

  def createSparkStreamingContext(parallelismNum: String, sparkJobName: String, sparkMaster: String, batchTime: Int): StreamingContext = {
    // System.setProperty("auto.offset.reset", "smallest")
    System.setProperty("spark.default.parallelism", parallelismNum)
    val sparkConf = new SparkConf().setAppName(sparkJobName).setMaster(sparkMaster)
    new StreamingContext(sparkConf, Seconds(batchTime))
  }

}
