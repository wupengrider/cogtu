package com.cogtu.realtime.log;

import com.cogtu.realtime.conf.SystemConf;
import com.cogtu.realtime.tools.JsonParseTool;
import org.json.JSONObject;
import scala.Serializable;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2015/6/4.
 */
public class ScrawlerLog implements Serializable {

    private String taskKey;

    private String hostIp;

    private String pid;

    private long startTime;

    private long endTime;

    private long mqSendTime;

    // 0:coredata, 1:spider
    private String source;

    // 目前用来标识代理不足的问题，当值为111时说明代理不足
    private String errorCode;

    private WeiboLog weibo = new WeiboLog();

    private List<PicInfo> pics = new ArrayList<PicInfo>();

    public static void main(String[] args) {
        ScrawlerLog sl = new ScrawlerLog();
        sl.testJson();
    }

    public void testJson() {
        try {
            // String jsonStr = "{\"weiboId\":\"bf5cc9ead32e\",\"ip\":\"117.121.26.197\",\"timestamp\":1432979588937,\"url\":\"http://search.maven.org/#search%7Cga%7C1%7Cmysql\",\"source\":\"scrawler\",\"pics\":[{\"picId\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"downloadTime\":5000,\"picSize\":1024,\"isDlSucc\":true},{\"picId\":\"537ff50ajw1esmfaqoga5j20zk0qon68\",\"downloadTime\":3000,\"picSize\":2048,\"isDlSucc\":true},{\"picId\":\"\",\"downloadTime\":0,\"picSize\":0,\"isDlSucc\":false}]}";
            // String jsonStr = "{\"taskKey\":\"519ff450d5ac\",\"hostIp\":\"117.121.26.197\",\"pid\":\"9caad0b8e814\",\"startTime\":1432979588937,\"endTime\":1432979588937,\"source\":1,\"pics\":[{\"picId\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"size\":1024,\"useTime\":29,\"status\":0,\"errorMsg\":\"\"},{\"picId\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"size\":2048,\"useTime\":29,\"status\":0,\"errorMsg\":\"\"},{\"picId\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"size\":0,\"useTime\":29,\"status\":1,\"errorMsg\":\"eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeerror\"}]}";
            String jsonStr = "{\"taskKey\":\"519ff450d5ac\",\"hostIp\":\"117.121.26.197\",\"pid\":\"9caad0b8e814\",\"startTime\":1432979588937,\"endTime\":143297959999,\"source\":\"spider\",\"errorCode\":\"scrawlerErrorCode\",\"weibo\":{\"isDownload\":1,\"agent\":\"weiboAgent\",\"status\":\"weiboStatus\",\"errorCode\":\"errorCode\"},\"pics\":[{\"picId\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"size\":1,\"useTime\":29,\"status\":0,\"errorCode\":\"\"},{\"picId\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"size\":1,\"useTime\":29,\"status\":0,\"errorCode\":\"\"},{\"picId\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"size\":1,\"useTime\":29,\"status\":1,\"errorCode\":\"404\"}]}";
            // ScrawlerLog sl = /*(ScrawlerLog)*/ JsonTool.toBean(jsonStr, ScrawlerLog.class);
            ScrawlerLog sl = JsonParseTool.jsonStr2ScrawlerLog(jsonStr);
            System.out.println(sl.getHostIp() + ", " + sl.getPics().size());
            for (int i = 0; i < sl.getPics().size(); i++) {
                System.out.println(sl.getPics().get(i).getPicId());
                if (sl.getPics().get(i).getStatus() == 0) {
                    System.out.println("success.");
                } else {
                    System.out.println("fail.");
                }
            }

            String js = "{\"weibo\":{},\"taskKey\":\"Ckx7MovuE\",\"pid\":\"10.0.0.5:1\",\"pics\":[],\"errorCode\":\"111\",\"source\":\"spider\",\"startTime\":1433501900375,\"hostIp\":\"10.0.0.5\",\"endTime\":1433501978239}";
            ScrawlerLog sll = JsonParseTool.jsonStr2ScrawlerLog(js);
            System.out.println(sll.getEndTime());

            JSONObject myJsonObject = new JSONObject(js);
            //获取对应的值
            String value1 = myJsonObject.getString("source");
            System.out.println("source=" + value1);
            myJsonObject = new JSONObject(jsonStr);
            value1 = myJsonObject.getString("source");
            System.out.println("2 source=" + value1);

            String reqLog = "{\"source\":\"reqLog\",\"ip\":\"117.121.26.197\",\"timestamp1\":1432979588937,\"timestamp2\":\"2015-05-30 17:53:08\",\"country\":\"中国\",\"province\":\"北京\",\"addrCode\":\"110000\",\"city\":\"北京\",\"browser\":\"Android browser|4\",\"os\":\"Android 4.3 Jelly Bean\",\"mediaId\":1,\"siteId\":0,\"pathId\":0,\"sessionId\":\"acdea88f-ea96-40ee-9796-1b56bfaab50d\",\"reqType\":1,\"reqs\":[{\"picId\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfay4ecmj20qo0zkais\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfb2u8kjj20vk0notjt\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfae83pkj20qo0zkwjr\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfauieukj20vk0noq7h\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfaixnq0j20zk0qoth5\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":28,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfaah5j5j20xc18ggy8\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfaceqlpj20qo0zkn27\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":28,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfa2iffzj20zk0qoag4\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":28,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"}]}";
            myJsonObject = new JSONObject(reqLog);
            value1 = myJsonObject.getString("source");
            System.out.println("3 source=" + value1);

            String slJson = "{\"weibo\":{\"status\":1,\"errorCode\":0,\"isDownload\":0,\"agent\":\"local\"},\"taskKey\":\"3850778508473178\",\"pid\":\"172.16.80.1:downloader_online-0\",\"pics\":[{\"status\":1,\"errorCode\":0,\"picId\":\"72635b6agw1esudfnav82j20ip0cgwg6\",\"useTime\":282,\"size\":25512},{\"status\":1,\"errorCode\":0,\"picId\":\"72635b6agw1esudflx0xhj20ip0cg40f\",\"useTime\":327,\"size\":29219},{\"status\":1,\"errorCode\":0,\"picId\":\"72635b6agw1esudfmacyfj20ip0cgwgj\",\"useTime\":321,\"size\":30667},{\"status\":1,\"errorCode\":0,\"picId\":\"72635b6agw1esudgekq6dj20ip0cmdhe\",\"useTime\":273,\"size\":23140},{\"status\":1,\"errorCode\":0,\"picId\":\"72635b6agw1esudfmqcz2j20ip0ipwg7\",\"useTime\":275,\"size\":24084},{\"status\":1,\"errorCode\":0,\"picId\":\"72635b6agw1esudfohfrxj20ip0cgq4t\",\"useTime\":276,\"size\":27902},{\"status\":1,\"errorCode\":0,\"picId\":\"72635b6agw1esudg2t7vwj20ip0cgq50\",\"useTime\":314,\"size\":30726},{\"status\":1,\"errorCode\":0,\"picId\":\"72635b6agw1esudgev69ej20ip0s4td0\",\"useTime\":318,\"size\":56792},{\"status\":1,\"errorCode\":0,\"picId\":\"72635b6agw1esudflifvaj20ip0s4tcv\",\"useTime\":473,\"size\":53813}],\"source\":\"spider\",\"startTime\":1433580826827,\"hostIp\":\"172.16.80.1\",\"mqSendTime\":12,\"endTime\":1433580829699}";
            sl = JsonParseTool.jsonStr2ScrawlerLog(slJson);
            System.out.println("pics.size=" + sl.getPics().size());

            slJson = "{\"weibo\":{\"status\":1,\"errorCode\":0,\"isDownload\":0,\"agent\":\"local\"},\"taskKey\":\"3839840435308286\",\"pid\":\"172.16.80.1:downloader_online-0\",\"pics\":[{\"status\":1,\"errorCode\":0,\"picId\":\"96310ec1jw1ervkoyv0bpj20c80go76b\",\"useTime\":238,\"size\":85238},{\"status\":1,\"errorCode\":0,\"picId\":\"96310ec1jw1ervkoz4nnaj20c80godhf\",\"useTime\":264,\"size\":69032},{\"status\":1,\"errorCode\":0,\"picId\":\"96310ec1jw1ervkoym9s6j20c80godhx\",\"useTime\":254,\"size\":87488},{\"status\":1,\"errorCode\":0,\"picId\":\"96310ec1jw1ervkoyu483j20c80gomz8\",\"useTime\":195,\"size\":86674},{\"status\":1,\"errorCode\":0,\"picId\":\"96310ec1jw1ervkoz2tadj20c80gomyz\",\"useTime\":218,\"size\":77462},{\"status\":1,\"errorCode\":0,\"picId\":\"96310ec1jw1ervkoz3gc7j20c80goabg\",\"useTime\":238,\"size\":61755},{\"status\":1,\"errorCode\":0,\"picId\":\"96310ec1jw1ervkoyxm1vj20c80go766\",\"useTime\":248,\"size\":80063},{\"status\":1,\"errorCode\":0,\"picId\":\"96310ec1jw1ervkoyqmrvj20c80goab8\",\"useTime\":526,\"size\":53607},{\"status\":1,\"errorCode\":0,\"picId\":\"96310ec1jw1ervkoz5sfxj20c80goq4w\",\"useTime\":272,\"size\":82697}],\"source\":\"spider\",\"startTime\":1433581788558,\"hostIp\":\"172.16.80.1\",\"mqSendTime\":10,\"endTime\":1433581791021}";
            myJsonObject = new JSONObject(slJson);
            Object obj = null;
            if (myJsonObject.getString(SystemConf.SC_KAFKA_TOPIC_SOURCE_ATTRI_NAME()).equals(SystemConf.SC_KAFKA_TOPIC_SOURCE_SPIDER())) {
                System.out.println("scrawlerLog: pics size is " + JsonParseTool.jsonStr2ScrawlerLog(jsonStr).getPics().size());
            }

            net.sf.json.JSONObject object = net.sf.json.JSONObject.fromObject(slJson);
            System.out.println("Json String is:" + object.toString());

            System.out.println(InetAddress.getLocalHost().getHostAddress());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
            System.out.println(sdf.format(new Date()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long getMqSendTime() {
        return mqSendTime;
    }

    public void setMqSendTime(long mqSendTime) {
        this.mqSendTime = mqSendTime;
    }

    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public WeiboLog getWeibo() {
        return weibo;
    }

    public void setWeibo(WeiboLog weibo) {
        this.weibo = weibo;
    }

    public List<PicInfo> getPics() {
        return pics;
    }

    public void setPics(List<PicInfo> pics) {
        this.pics = pics;
    }
}









