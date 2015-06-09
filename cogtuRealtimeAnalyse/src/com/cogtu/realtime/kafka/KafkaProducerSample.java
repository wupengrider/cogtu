package com.cogtu.realtime.kafka;

import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import breeze.util.ArrayUtil;
import com.cogtu.realtime.conf.SystemConf;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaProducerSample {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Properties props = new Properties();
        System.out.println("12345678 hello!!!!! params: " + Arrays.toString(args));
        // props.put("zk.connect", "172.16.40.1:2181");
        props.put("zk.connect", "172.16.40.1:2181,172.16.40.2:2181,172.16.40.3:2181");
        // props.setProperty("metadata.broker.list", "172.16.40.1:9092");
        props.setProperty("metadata.broker.list", "172.16.40.1:9092,172.16.40.2:9092,172.16.40.3:9092");
        props.setProperty("serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "1");
        ProducerConfig config = new ProducerConfig(props);
        Producer<String, String> producer = new Producer<String, String>(config);
        String log_type1_9reqs = "{\"source\":\"req\",\"ip\":\"117.121.26.197\",\"timestamp1\":\"19\",\"timestamp2\":\"2015-05-30 17:53:08\",\"slotid\":\"slotId222\",\"pageuri\":\"pageUri1111\",\"ref\":\"ref22222\",\"country\":\"中国\",\"province\":\"province22222\",\"city\":\"北京\",\"addrcode\":\"addrCode\",\"browser\":\"browser22222\",\"os\":\"windows\",\"uid\":\"acdea88f-ea96-40ee-9796-1b56bfaab50d\",\"reqtype\":1,\"imp\":[{\"impid\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advertisersid\":1,\"projectid\":29,\"campaignid\":27,\"creativeid\":34,\"price\":100,\"pricetype\":1000},{\"impid\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advertisersid\":1,\"projectid\":29,\"campaignid\":27,\"creativeid\":34,\"price\":100,\"pricetype\":1000},{\"impid\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advertisersid\":1,\"projectid\":29,\"campaignid\":27,\"creativeid\":34,\"price\":100,\"pricetype\":1000},{\"impid\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advertisersid\":1,\"projectid\":29,\"campaignid\":27,\"creativeid\":34,\"price\":100,\"pricetype\":1000},{\"impid\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advertisersid\":1,\"projectid\":29,\"campaignid\":27,\"creativeid\":35,\"price\":100,\"pricetype\":1000},{\"impid\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advertisersid\":1,\"projectid\":29,\"campaignid\":27,\"creativeid\":35,\"price\":100,\"pricetype\":1000},{\"impid\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advertisersid\":1,\"projectid\":29,\"campaignid\":27,\"creativeid\":34,\"price\":100,\"pricetype\":1000},{\"impid\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advertisersid\":2,\"projectid\":29,\"campaignid\":27,\"creativeid\":34,\"price\":100,\"pricetype\":1000},{\"impid\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advertisersid\":3,\"projectid\":29,\"campaignid\":27,\"creativeid\":34,\"price\":100,\"pricetype\":1000}]}";
        String log_type1_0reqs = "{\"source\":\"req\",\"ip\":\"117.121.26.197\",\"timestamp1\":\"19\",\"timestamp2\":\"2015-05-30 17:53:08\",\"slotid\":\"slotId222\",\"pageuri\":\"pageUri2222\",\"ref\":\"ref22222\",\"country\":\"中国\",\"province\":\"province22222\",\"city\":\"北京\",\"addrcode\":\"addrCode\",\"browser\":\"browser22222\",\"os\":\"windows\",\"uid\":\"acdea88f-ea96-40ee-9796-1b56bfaab50d\",\"reqtype\":1,\"imp\":[]}";
        String log_type1_91reqs = "{\"source\":\"req\",\"ip\":\"117.121.26.197\",\"timestamp1\":\"19\",\"timestamp2\":\"2015-05-30 17:53:08\",\"slotid\":\"slotId222\",\"pageuri\":\"pageUri2222\",\"ref\":\"ref22222\",\"country\":\"中国\",\"province\":\"province22222\",\"city\":\"北京\",\"addrcode\":\"addrCode\",\"browser\":\"browser22222\",\"os\":\"windows\",\"uid\":\"acdea88f-ea96-40ee-9796-1b56bfaab50d\",\"reqtype\":1,\"imp\":[{\"impid\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advertisersid\":1,\"projectid\":29,\"campaignid\":27,\"creativeid\":34,\"price\":100,\"pricetype\":1000}]}";
        String log_type2_1reqs = "{\"source\":\"req\",\"ip\":\"117.121.26.197\",\"timestamp1\":\"19\",\"timestamp2\":\"2015-05-30 17:53:08\",\"slotid\":\"slotId222\",\"pageuri\":\"pageUri2222\",\"ref\":\"ref22222\",\"country\":\"中国\",\"province\":\"province22222\",\"city\":\"北京\",\"addrcode\":\"addrCode\",\"browser\":\"browser22222\",\"os\":\"windows\",\"uid\":\"acdea88f-ea96-40ee-9796-1b56bfaab50d\",\"reqtype\":2,\"imp\":[{\"impid\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advertisersid\":1,\"projectid\":29,\"campaignid\":27,\"creativeid\":34,\"price\":100,\"pricetype\":1000}]}";
        String log_type2_0reqs = "{\"source\":\"req\",\"ip\":\"117.121.26.197\",\"timestamp1\":\"19\",\"timestamp2\":\"2015-05-30 17:53:08\",\"slotid\":\"slotId222\",\"pageuri\":\"pageUri2222\",\"ref\":\"ref22222\",\"country\":\"中国\",\"province\":\"province22222\",\"city\":\"北京\",\"addrcode\":\"addrCode\",\"browser\":\"browser22222\",\"os\":\"windows\",\"uid\":\"acdea88f-ea96-40ee-9796-1b56bfaab50d\",\"reqtype\":2,\"imp\":[]}";
        String log_type3_1reqs = "{\"source\":\"req\",\"ip\":\"117.121.26.197\",\"timestamp1\":\"19\",\"timestamp2\":\"2015-05-30 17:53:08\",\"slotid\":\"slotId222\",\"pageuri\":\"pageUri2222\",\"ref\":\"ref22222\",\"country\":\"中国\",\"province\":\"province22222\",\"city\":\"北京\",\"addrcode\":\"addrCode\",\"browser\":\"browser22222\",\"os\":\"windows\",\"uid\":\"acdea88f-ea96-40ee-9796-1b56bfaab50d\",\"reqtype\":3,\"imp\":[{\"impid\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advertisersid\":1,\"projectid\":29,\"campaignid\":27,\"creativeid\":34,\"price\":100,\"pricetype\":1000}]}";
        KeyedMessage<String, String> data19 = new KeyedMessage<String, String>("coreData", log_type1_9reqs);
        KeyedMessage<String, String> data191 = new KeyedMessage<String, String>("coreData", log_type1_91reqs);
        KeyedMessage<String, String> data10 = new KeyedMessage<String, String>("coreData", log_type1_0reqs);
        KeyedMessage<String, String> data21 = new KeyedMessage<String, String>("coreData", log_type2_1reqs);
        KeyedMessage<String, String> data20 = new KeyedMessage<String, String>("coreData", log_type2_0reqs);
        KeyedMessage<String, String> data31 = new KeyedMessage<String, String>("coreData", log_type3_1reqs);

        String logSpider = "{\"taskKey\":\"519ff450d5ac\",\"hostIp\":\"117.121.26.197\",\"mqSendTime\":10,\"pid\":\"9caad0b8e814\",\"startTime\":1432979588937,\"endTime\":143297959999,\"source\":\"spider\",\"errorCode\":\"scrawlerErrorCode\",\"weibo\":{\"isDownload\":1,\"agent\":\"weiboAgent\",\"status\":\"weiboStatus\",\"errorCode\":\"errorCode\"},\"pics\":[{\"picId\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"size\":1,\"useTime\":29,\"status\":0,\"errorCode\":\"\"},{\"picId\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"size\":1,\"useTime\":29,\"status\":0,\"errorCode\":\"\"},{\"picId\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"size\":1,\"useTime\":29,\"status\":1,\"errorCode\":\"404\"}]}";
        KeyedMessage<String, String> dataSpider = new KeyedMessage<String, String>("spider_wup", logSpider);
        String logSpider_agentLack = "{\"weibo\":{},\"taskKey\":\"Ckx7MovuE\",\"mqSendTime\":10,\"pid\":\"10.0.0.5:1\",\"pics\":[],\"errorCode\":\"111\",\"source\":\"spider\",\"startTime\":1433501900375,\"hostIp\":\"10.0.0.5\",\"endTime\":1433501978239}";
        KeyedMessage<String, String> dataSpiderAgentLack = new KeyedMessage<String, String>("spider_wup", logSpider_agentLack);
        String logSpiderNormal = "{\"weibo\":{\"status\":1,\"errorCode\":0,\"isDownload\":0,\"mqSendTime\":10,\"agent\":\"local\"},\"taskKey\":\"Ckx7MovuE\",\"pid\":\"10.0.0.5:1\",\"pics\":[{\"status\":1,\"errorCode\":0,\"picId\":1,\"useTime\":338,\"size\":31995},{\"status\":1,\"errorCode\":0,\"picId\":1,\"useTime\":277,\"size\":41360},{\"status\":1,\"errorCode\":0,\"picId\":1,\"useTime\":3179,\"size\":38508},{\"status\":1,\"errorCode\":0,\"picId\":1,\"useTime\":87,\"size\":36355},{\"status\":1,\"errorCode\":0,\"picId\":1,\"useTime\":125,\"size\":25545},{\"status\":1,\"errorCode\":0,\"picId\":1,\"useTime\":412,\"size\":45833},{\"status\":1,\"errorCode\":0,\"picId\":1,\"useTime\":3208,\"size\":52678},{\"status\":1,\"errorCode\":0,\"picId\":1,\"useTime\":486,\"size\":40168},{\"status\":1,\"errorCode\":0,\"picId\":1,\"useTime\":253,\"size\":37087}],\"source\":\"spider\",\"startTime\":1433498373065,\"hostIp\":\"10.0.0.5\",\"endTime\":1433498464426}";
        KeyedMessage<String, String> dataSpiderNormal = new KeyedMessage<String, String>("spider_wup", logSpiderNormal);

        try {
            while (true) {
                String currentTime = new Date().toString();
                for (int i = 0; i < Integer.valueOf(args[0]); i++) {
                    producer.send(data19);
                    producer.send(data10);
                    producer.send(data191);
                    producer.send(data21);
                    producer.send(data21);
                    producer.send(data20);
                    producer.send(data31);

                    producer.send(dataSpider);
                    producer.send(dataSpiderAgentLack);
                    producer.send(dataSpiderNormal);
                }
                System.out.println("Send " + args[0] + " records in 5 sec. timeid is " + currentTime);
                Thread.sleep(5000);
            }

			/*
             * int i = 1; while (i < 1000) { producer.send(data);
			 * System.out.println("send one data."); }
			 */
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.close();
    }

}
