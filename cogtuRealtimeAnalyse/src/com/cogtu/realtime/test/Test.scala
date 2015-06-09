package com.cogtu.realtime.test

import com.cogtu.realtime.logAnalyse.RealTimeLogAnalyse
import com.cogtu.realtime.tools.JsonParseTool

/**
 * Created by lenovo on 2015/6/3.
 */
object Test {
  def main(args: Array[String]) {
    // testJson
    testArraySplit
  }

  def testArraySplit {
    val str = "1,2,3,4,5"
    val Array(a, b, c, d, e) = str.split(",")
    println(s"a=$a, b=$b, c=$c")
  }

  def testJson {
    val log_type1_9reqs = "{\"type\":\"reqLog\",\"ip\":\"117.121.26.197\",\"timestamp1\":19,\"timestamp2\":\"2015-05-30 17:53:08\",\"country\":\"中国\",\"province\":\"北京\",\"addrCode\":\"110000\",\"city\":\"北京\",\"browser\":\"Android browser|4\",\"os\":\"Android 4.3 Jelly Bean\",\"mediaId\":1,\"siteId\":0,\"pathId\":0,\"sessionId\":\"acdea88f-ea96-40ee-9796-1b56bfaab50d\",\"reqType\":1,\"reqs\":[{\"picId\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfay4ecmj20qo0zkais\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfb2u8kjj20vk0notjt\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfae83pkj20qo0zkwjr\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfauieukj20vk0noq7h\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfaixnq0j20zk0qoth5\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":28,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfaah5j5j20xc18ggy8\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfaceqlpj20qo0zkn27\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":28,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfa2iffzj20zk0qoag4\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":28,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"}]}"
    val log_type1_0reqs = "{\"type\":\"reqLog\",\"ip\":\"117.121.26.197\",\"timestamp1\":10,\"timestamp2\":\"2015-05-30 17:53:08\",\"country\":\"中国\",\"province\":\"北京\",\"addrCode\":\"110000\",\"city\":\"北京\",\"browser\":\"Androidbrowser|4\",\"os\":\"Android4.3JellyBean\",\"mediaId\":1,\"siteId\":0,\"pathId\":0,\"sessionId\":\"acdea88f-ea96-40ee-9796-1b56bfaab50d\",\"reqType\":1,\"reqs\":[]}"
    val log_type1_91reqs = "{\"type\":\"reqLog\",\"ip\":\"117.121.26.197\",\"timestamp1\":191,\"timestamp2\":\"2015-05-30 17:53:08\",\"country\":\"中国\",\"province\":\"北京\",\"addrCode\":\"110000\",\"city\":\"北京\",\"browser\":\"Androidbrowser|4\",\"os\":\"Android4.3JellyBean\",\"mediaId\":1,\"siteId\":0,\"pathId\":0,\"sessionId\":\"acdea88f-ea96-40ee-9796-1b56bfaab50d\",\"reqType\":1,\"reqs\":[{\"picId\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"},{\"picId\":\"537ff50ajw1esmfay4ecmj20qo0zkais\"},{\"picId\":\"537ff50ajw1esmfb2u8kjj20vk0notjt\"},{\"picId\":\"537ff50ajw1esmfae83pkj20qo0zkwjr\"},{\"picId\":\"537ff50ajw1esmfauieukj20vk0noq7h\"},{\"picId\":\"537ff50ajw1esmfaixnq0j20zk0qoth5\"},{\"picId\":\"537ff50ajw1esmfaah5j5j20xc18ggy8\"},{\"picId\":\"537ff50ajw1esmfaceqlpj20qo0zkn27\"},{\"picId\":\"537ff50ajw1esmfa2iffzj20zk0qoag4\"}]}"
    val log_type2_1reqs = "{\"type\":\"reqLog\",\"ip\":\"117.121.26.197\",\"timestamp1\":21,\"timestamp2\":\"2015-05-30 17:53:08\",\"country\":\"中国\",\"province\":\"北京\",\"addrCode\":\"110000\",\"city\":\"北京\",\"browser\":\"Androidbrowser|4\",\"os\":\"Android4.3JellyBean\",\"mediaId\":1,\"siteId\":0,\"pathId\":0,\"sessionId\":\"acdea88f-ea96-40ee-9796-1b56bfaab50d\",\"reqType\":2,\"reqs\":[{\"picId\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"}]}"
    val log_type2_0reqs = "{\"type\":\"reqLog\",\"ip\":\"117.121.26.197\",\"timestamp1\":20,\"timestamp2\":\"2015-05-30 17:53:08\",\"country\":\"中国\",\"province\":\"北京\",\"addrCode\":\"110000\",\"city\":\"北京\",\"browser\":\"Androidbrowser|4\",\"os\":\"Android4.3JellyBean\",\"mediaId\":1,\"siteId\":0,\"pathId\":0,\"sessionId\":\"acdea88f-ea96-40ee-9796-1b56bfaab50d\",\"reqType\":2,\"reqs\":[]}"
    val log_type3_1reqs = "{\"type\":\"reqLog\",\"ip\":\"117.121.26.197\",\"timestamp1\":31,\"timestamp2\":\"2015-05-30 17:53:08\",\"country\":\"中国\",\"province\":\"北京\",\"addrCode\":\"110000\",\"city\":\"北京\",\"browser\":\"Androidbrowser|4\",\"os\":\"Android4.3JellyBean\",\"mediaId\":1,\"siteId\":0,\"pathId\":0,\"sessionId\":\"acdea88f-ea96-40ee-9796-1b56bfaab50d\",\"reqType\":3,\"reqs\":[{\"picId\":\"537ff50ajw1esmfaqoga5j20zk0qon67\",\"advId\":1,\"adProjectId\":29,\"adCampaignId\":27,\"adId\":34,\"price\":1000,\"priceType\":\"CPM\"}]}"

    val r19 = JsonParseTool.jsonStr2ReqLog(log_type1_9reqs)
    val r10 = JsonParseTool.jsonStr2ReqLog(log_type1_0reqs)
    val r191 = JsonParseTool.jsonStr2ReqLog(log_type1_91reqs)
    val r21 = JsonParseTool.jsonStr2ReqLog(log_type2_1reqs)
    val r20 = JsonParseTool.jsonStr2ReqLog(log_type2_0reqs)
    val r31 = JsonParseTool.jsonStr2ReqLog(log_type3_1reqs)

    println("1111111111111111111")


  }

}
