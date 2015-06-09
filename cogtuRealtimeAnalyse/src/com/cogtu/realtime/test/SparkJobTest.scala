package com.cogtu.realtime.test

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.SparkContext._

/**
 * Created by lenovo on 2015/6/4.
 */
object SparkJobTest {

  def main (args: Array[String]) {
    test
  }

  def test {
    println("ttttttttestttttttttttt")
    val sc = new SparkContext(new SparkConf().setAppName("test").setMaster("yarn-cluster"))
    val player = sc.parallelize(List(("AC", "Kaka"), ("AC", "bt"), ("gz", "zhengzhi"), ("qwert", "abc")))
    val team = sc.parallelize(List(("AC", 5), ("AC", "add"), ("gz", 3)))
    val v = player.join(team).collect
    println(v.mkString("#"))
    println("tttttttttttttttttttttttttttttttttttttttt")

  }
}
