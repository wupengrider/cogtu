package com.cogtu.realtime.kafka

import java.util.Properties
import kafka.producer.{KeyedMessage, ProducerConfig}
import kafka.javaapi.producer.Producer
import scala.collection.mutable.ArrayBuffer

object ProducerTest {
  
  def main(args: Array[String]) {
    System.out.println("this is producer")
    val props: Properties = new Properties
    props.put("zookeeper.connect", "172.16.40.1:2181")
    props.put("serializer.class", "kafka.serializer.StringEncoder")
    props.put("metadata.broker.list", "172.16.40.1:9092")
    val config: ProducerConfig = new ProducerConfig(props)
    val producer: Producer[String, String] = new Producer[String, String](config)
    
    /*val seq: Seq[KeyedMessage[String, String]] = new Seq(new KeyedMessage[String, String]("test", "test" + 1))
    val v = new KeyedMessage[String, String]("test", "test" + 2)
    val seq2 = seq ++ v
    producer.send(seq2)*/

    // for (i <- 0 until 10)
    // producer.send(new KeyedMessage[String, String]("test4", "1111111111"), new KeyedMessage[String, String]("test4", "222222222"))

    val list: java.util.List[KeyedMessage[String, String]] = new java.util.ArrayList[KeyedMessage[String, String]]()
    list.add(new KeyedMessage[String, String]("ttttwup", "ab1111111111"))
    list.add(new KeyedMessage[String, String]("ttttwup", "ab2222222222"))

    // for (i <- 0 until 10)
    // producer.send(new KeyedMessage[String, String]("test4", "1111111111"), new KeyedMessage[String, String]("test4", "1111111111"))
    // producer.send(new KeyedMessage[String, String]("my-replicated-topic9", "b31111111111"))
    producer.send(list)

    /*new KeyedMessage[String, String]("test", "test" + 1)
    {
      var i: Int = 0
      while (i < 10) {
        producer.send(new KeyedMessage[String, String]("test", "test" + i))
        ({
          i += 1; i - 1
        })
      }
    }*/
  }

}