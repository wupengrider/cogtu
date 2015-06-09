package com.cogtu.realtime.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Date;
import java.util.Properties;

/**
 * Created by lenovo on 2015/6/3.
 */
public class KafkaProducerThread extends Thread {
    private long intevalTime = 0;
    private long count = 0;
    private Producer<String, String> producer = null;
    private boolean stopFlag = false;

    public KafkaProducerThread(long intevalTime, long count) {
        this.intevalTime = intevalTime;
        this.count = count;
        Properties props = new Properties();
        System.out.println("hello!!!!! thread " + this.getName());
        props.put("zk.connect", "172.16.40.1:2181");
        props.setProperty("metadata.broker.list", "172.16.40.1:9092");
        props.setProperty("serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "1");
        ProducerConfig config = new ProducerConfig(props);
        this.producer = new Producer<String, String>(config);
    }

    @Override
    public void run() {
        try {
            String log_type1_9reqs = "{\"type\":\"reqLog\",\"ip\":\"117.121.26.197\",\"timestamp1\":19,\"timestamp2\":\"`currenttimestamp`\",\"country\":\"中国\",\"province\":\"北京\",\"addrCode\":\"110000\",\"city\":\"北京\",\"browser\":\"Android browser|4\",\"os\":\"Android 4.3 Jelly Bean\",\"mediaId\":1,\"siteId\":0,\"pathId\":0,\"sessionId\":\"acdea88f-ea96-40ee-9796-1b56bfaab50d\",\"reqType\":1,\"reqs\":[{\"picId\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfay4ecmj20qo0zkais\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfb2u8kjj20vk0notjt\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfae83pkj20qo0zkwjr\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfauieukj20vk0noq7h\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfaixnq0j20zk0qoth5\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":28,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfaah5j5j20xc18ggy8\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfaceqlpj20qo0zkn27\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":28,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfa2iffzj20zk0qoag4\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":28,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"}]}";
            String log_type1_0reqs = "{\"type\":\"reqLog\",\"ip\":\"117.121.26.197\",\"timestamp1\":10,\"timestamp2\":\"`currenttimestamp`\",\"country\":\"中国\",\"province\":\"北京\",\"addrCode\":\"110000\",\"city\":\"北京\",\"browser\":\"Androidbrowser|4\",\"os\":\"Android4.3JellyBean\",\"mediaId\":1,\"siteId\":0,\"pathId\":0,\"sessionId\":\"acdea88f-ea96-40ee-9796-1b56bfaab50d\",\"reqType\":1,\"reqs\":[]}";
            String log_type1_91reqs = "{\"type\":\"reqLog\",\"ip\":\"117.121.26.197\",\"timestamp1\":191,\"timestamp2\":\"`currenttimestamp`\",\"country\":\"中国\",\"province\":\"北京\",\"addrCode\":\"110000\",\"city\":\"北京\",\"browser\":\"Androidbrowser|4\",\"os\":\"Android4.3JellyBean\",\"mediaId\":1,\"siteId\":0,\"pathId\":0,\"sessionId\":\"acdea88f-ea96-40ee-9796-1b56bfaab50d\",\"reqType\":1,\"reqs\":[{\"picId\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfay4ecmj20qo0zkais\"},{\"picId\":\"537ff50ajw1esmfb2u8kjj20vk0notjt\"},{\"picId\":\"537ff50ajw1esmfae83pkj20qo0zkwjr\"},{\"picId\":\"537ff50ajw1esmfauieukj20vk0noq7h\"},{\"picId\":\"537ff50ajw1esmfaixnq0j20zk0qoth5\"},{\"picId\":\"537ff50ajw1esmfaah5j5j20xc18ggy8\"},{\"picId\":\"537ff50ajw1esmfaceqlpj20qo0zkn27\"},{\"picId\":\"537ff50ajw1esmfa2iffzj20zk0qoag4\"}]}";
            String log_type2_1reqs = "{\"type\":\"reqLog\",\"ip\":\"117.121.26.197\",\"timestamp1\":21,\"timestamp2\":\"`currenttimestamp`\",\"country\":\"中国\",\"province\":\"北京\",\"addrCode\":\"110000\",\"city\":\"北京\",\"browser\":\"Androidbrowser|4\",\"os\":\"Android4.3JellyBean\",\"mediaId\":1,\"siteId\":0,\"pathId\":0,\"sessionId\":\"acdea88f-ea96-40ee-9796-1b56bfaab50d\",\"reqType\":2,\"reqs\":[{\"picId\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"}]}";
            String log_type2_0reqs = "{\"type\":\"reqLog\",\"ip\":\"117.121.26.197\",\"timestamp1\":20,\"timestamp2\":\"`currenttimestamp`\",\"country\":\"中国\",\"province\":\"北京\",\"addrCode\":\"110000\",\"city\":\"北京\",\"browser\":\"Androidbrowser|4\",\"os\":\"Android4.3JellyBean\",\"mediaId\":1,\"siteId\":0,\"pathId\":0,\"sessionId\":\"acdea88f-ea96-40ee-9796-1b56bfaab50d\",\"reqType\":2,\"reqs\":[]}";
            String log_type3_1reqs = "{\"type\":\"reqLog\",\"ip\":\"117.121.26.197\",\"timestamp1\":31,\"timestamp2\":\"`currenttimestamp`\",\"country\":\"中国\",\"province\":\"北京\",\"addrCode\":\"110000\",\"city\":\"北京\",\"browser\":\"Androidbrowser|4\",\"os\":\"Android4.3JellyBean\",\"mediaId\":1,\"siteId\":0,\"pathId\":0,\"sessionId\":\"acdea88f-ea96-40ee-9796-1b56bfaab50d\",\"reqType\":3,\"reqs\":[{\"picId\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"}]}";

            String currentTime = new Date().toString();
            KeyedMessage<String, String> data19 = new KeyedMessage<String, String>("ttttwup", log_type1_9reqs/*.replaceAll("`currenttimestamp`", currentTime)*/);
            KeyedMessage<String, String> data191 = new KeyedMessage<String, String>("ttttwup", log_type1_91reqs/*.replaceAll("`currenttimestamp`", currentTime)*/);
            KeyedMessage<String, String> data10 = new KeyedMessage<String, String>("ttttwup", log_type1_0reqs/*.replaceAll("`currenttimestamp`", currentTime)*/);
            KeyedMessage<String, String> data21 = new KeyedMessage<String, String>("ttttwup", log_type2_1reqs/*.replaceAll("`currenttimestamp`", currentTime)*/);
            KeyedMessage<String, String> data20 = new KeyedMessage<String, String>("ttttwup", log_type2_0reqs/*.replaceAll("`currenttimestamp`", currentTime)*/);
            KeyedMessage<String, String> data31 = new KeyedMessage<String, String>("ttttwup", log_type3_1reqs/*.replaceAll("`currenttimestamp`", currentTime)*/);
            while (!stopFlag) {
                long time1 = System.currentTimeMillis();
                for (int i = 0; i < count; i++) {
                    producer.send(data19);
                    producer.send(data10);
                    producer.send(data191);
                    producer.send(data21);
                    producer.send(data20);
                    producer.send(data31);
                }

                long sleepTime = 5000 - (System.currentTimeMillis() - time1);
                System.out.println(this.getName() + " send " + count + " records in 5 sec. TimeId is " + currentTime + ", sleep for " + sleepTime + " msec.");
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
        System.out.println("Thread " + this.getName() + " was stopped!");
    }

    public void stopThread() {
        this.stopFlag = true;
    }

}
