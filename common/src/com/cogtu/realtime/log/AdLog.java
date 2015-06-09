package com.cogtu.realtime.log;

import com.fasterxml.jackson.annotation.JsonProperty;
import scala.Serializable;

public class AdLog implements Serializable {
    // 广告位ID
    @JsonProperty("impid")
    private String impId;
    // 广告主ID
    @JsonProperty("advertisersid")
    private Integer advertisersId;
    // 广告项目ID
    @JsonProperty("projectid")
    private Integer projectId;
    // 广告计划ID
    @JsonProperty("campaignid")
    private Integer campaignId;
    // 广告ID
    @JsonProperty("creativeid")
    private Integer creativeId;
    // 价格(换算厘)
    private Integer price;
    // 类型 CPC/CPM
    @JsonProperty("pricetype")
    private String priceType;

    // 广告位是否被填充
    // 广告位数据是否合法 -- 当reqType=1时如果其下的属性不为空，那么说明该广告位被成功填充了
    public boolean isAdLogLegal() {
        return impId != null && impId != "" && advertisersId > 0 && projectId > 0 && null != priceType && !"".equals(priceType);
    }

    public String getImpId() {
        return impId;
    }

    public void setImpId(String impId) {
        this.impId = impId;
    }

    public Integer getAdvertisersId() {
        return advertisersId;
    }

    public void setAdvertisersId(Integer advertisersId) {
        this.advertisersId = advertisersId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    public Integer getCreativeId() {
        return creativeId;
    }

    public void setCreativeId(Integer creativeId) {
        this.creativeId = creativeId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }


}
