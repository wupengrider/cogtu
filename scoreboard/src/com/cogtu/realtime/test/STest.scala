package com.cogtu.realtime.test

import redis.clients.jedis.Jedis
import com.cogtu.realtime.tools.SingleJedis

/**
 * Created by lenovo on 2015/6/9.
 */
object STest {

  def main(args: Array[String]) {
    testRedis
  }

  def testRedis {
    val jedis = SingleJedis.getInstance()
    jedis.lpush("testStack", "0")
    jedis.lpush("testStack", "1")
    jedis.lpush("testStack", "2")
    jedis.lpush("testStack", "3")
    jedis.lpush("testStack", "4")

    println(jedis.lpop("testStack"))
    println(jedis.lpop("testStack"))
    println(jedis.lpop("testStack"))
    println(jedis.lpop("testStack"))
    println(jedis.lpop("testStack"))
    println(jedis.lpop("testStack"))

    for (i <- 0 until 20) {
      jedis.lpush("testStack", i.toString)
    }
    jedis.ltrim("testStack", 0, 100)

    for (i <- 0 until 20) {
      println("testStack pop:" + jedis.lpop("testStack"))
    }


  }


}
