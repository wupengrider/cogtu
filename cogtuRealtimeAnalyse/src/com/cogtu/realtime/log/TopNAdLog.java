package com.cogtu.realtime.log;

import scala.Serializable;

/**
 * Created by lenovo on 2015/6/8.
 */
public class TopNAdLog implements Serializable {

    private int adId;

    private int advId;

    private int adProjectId;

    private int adCampaignId;

    private int count;

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public int getAdvId() {
        return advId;
    }

    public void setAdvId(int advId) {
        this.advId = advId;
    }

    public int getAdProjectId() {
        return adProjectId;
    }

    public void setAdProjectId(int adProjectId) {
        this.adProjectId = adProjectId;
    }

    public int getAdCampaignId() {
        return adCampaignId;
    }

    public void setAdCampaignId(int adCampaignId) {
        this.adCampaignId = adCampaignId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
