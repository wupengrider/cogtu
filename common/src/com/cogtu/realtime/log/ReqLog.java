package com.cogtu.realtime.log;

import com.cogtu.realtime.tools.JsonTool;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;
import scala.Serializable;

import java.util.ArrayList;
import java.util.List;

/*
 * Request Log
 */
public class ReqLog extends CogtuLog implements Serializable {
	public static final int REQ_TYPE_AD_REQ = 1;
	public static final int REQ_TYPE_AD_IMP = 2;
	public static final int REQ_TYPE_AD_CLICK = 3;
	
	//1:广告请求 2:广告展示 3：广告点击
	@JsonProperty("reqtype") 
	private Integer reqType;
	
	@JsonProperty("imp") 
	private List<AdLog> reqs = new ArrayList<AdLog>();

    public static void main(String[] args) {
        try {
            // String log = "{\"type\":\"reqLog\",\"ip\":\"117.121.26.197\",\"timestamp1\":1432979588937,\"timestamp2\":\"2015-05-30 17:53:08\",\"country\":\"中国\",\"province\":\"北京\",\"addrCode\":\"110000\",\"city\":\"北京\",\"browser\":\"Android browser|4\",\"os\":\"Android 4.3 Jelly Bean\",\"mediaId\":1,\"siteId\":0,\"pathId\":0,\"sessionId\":\"acdea88f-ea96-40ee-9796-1b56bfaab50d\",\"reqType\":1,\"reqs\":[{\"picId\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfay4ecmj20qo0zkais\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfb2u8kjj20vk0notjt\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfae83pkj20qo0zkwjr\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfauieukj20vk0noq7h\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfaixnq0j20zk0qoth5\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":28,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfaah5j5j20xc18ggy8\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfaceqlpj20qo0zkn27\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":28,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfa2iffzj20zk0qoag4\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":28,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"}]}";
            // String log = "{\"source\":\"req\",\"ip\":\"117.121.26.197\",\"timestamp1\":19,\"timestamp2\":\"2015-05-30 17:53:08\",\"country\":\"中国\",\"province\":\"北京\",\"addrCode\":\"110000\",\"city\":\"北京\",\"browser\":\"Androidbrowser|4\",\"os\":\"Android4.3JellyBean\",\"sessionId\":\"acdea88f-ea96-40ee-9796-1b56bfaab50d\",\"reqType\":1,\"reqs\":[{\"picId\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfay4ecmj20qo0zkais\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfb2u8kjj20vk0notjt\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfae83pkj20qo0zkwjr\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfauieukj20vk0noq7h\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfaixnq0j20zk0qoth5\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":28,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfaah5j5j20xc18ggy8\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":28,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfaceqlpj20qo0zkn27\",\"advId\":2,\"adProjectId\":29,\"adCampaignId\":28,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfa2iffzj20zk0qoag4\",\"advId\":2,\"adProjectId\":29,\"adCampaignId\":28,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"}]}";
            String log = "{\"source\":\"req\",\"ip\":\"117.121.26.197\",\"timestamp1\":\"19\",\"timestamp2\":\"2015-05-30 17:53:08\",\"slotid\":\"slotId222\",\"pageuri\":\"pageUri2222\",\"ref\":\"ref22222\",\"country\":\"中国\",\"province\":\"province22222\",\"city\":\"北京\",\"addrcode\":\"addrCode\",\"browser\":\"browser22222\",\"os\":\"windows\",\"uid\":\"acdea88f-ea96-40ee-9796-1b56bfaab50d\",\"reqtype\":1,\"imp\":[{\"impid\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advertisersid\":1,\"projectid\":29,\"campaignid\":27,\"creativeid\":34,\"price\":100,\"pricetype\":1000},{\"impid\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advertisersid\":1,\"projectid\":29,\"campaignid\":27,\"creativeid\":34,\"price\":100,\"pricetype\":1000},{\"impid\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advertisersid\":1,\"projectid\":29,\"campaignid\":27,\"creativeid\":34,\"price\":100,\"pricetype\":1000},{\"impid\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advertisersid\":1,\"projectid\":29,\"campaignid\":27,\"creativeid\":34,\"price\":100,\"pricetype\":1000},{\"impid\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advertisersid\":1,\"projectid\":29,\"campaignid\":27,\"creativeid\":34,\"price\":100,\"pricetype\":1000},{\"impid\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advertisersid\":1,\"projectid\":29,\"campaignid\":27,\"creativeid\":34,\"price\":100,\"pricetype\":1000},{\"impid\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advertisersid\":1,\"projectid\":29,\"campaignid\":27,\"creativeid\":34,\"price\":100,\"pricetype\":1000},{\"impid\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advertisersid\":1,\"projectid\":29,\"campaignid\":27,\"creativeid\":34,\"price\":100,\"pricetype\":1000},{\"impid\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advertisersid\":1,\"projectid\":29,\"campaignid\":27,\"creativeid\":34,\"price\":100,\"pricetype\":1000}]}";
            ReqLog rl = JsonTool.toBean(log, ReqLog.class);
            System.out.println(rl.getReqType());
            for (int i = 0; i < rl.getReqs().size(); i++) {
                System.out.println("impId is " + rl.getReqs().get(i).getImpId());
            }

            JSONObject myJsonObject = new JSONObject(log);
            //获取对应的值
            String value1 = myJsonObject.getString("source");
            System.out.println("source=" + value1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public List<AdLog> getReqs() {
		return reqs;
	}

	public void setReqs(List<AdLog> reqs) {
		this.reqs = reqs;
	}

	public Integer getReqType() {
		return reqType;
	}

	public void setReqType(Integer reqType) {
		this.reqType = reqType;
	}
}