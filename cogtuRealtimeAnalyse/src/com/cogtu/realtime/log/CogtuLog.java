package com.cogtu.realtime.log;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import scala.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "source")
@JsonSubTypes({@Type(value = TrackLog.class, name = "track"), @Type(value = ReqLog.class, name = "req")})
public class CogtuLog implements Serializable {

    // IP地址
    private String ip;
    // 时间戳
    private long timestamp1;
    // 时间戳
    private Date timestamp2;

    // slot ID
    @JsonProperty("slotid")
    private String slotId;

    @JsonProperty("pageuri")
    private String pageUri;
    // 来源页
    private String ref;

    // 国家
    private String country;
    // 省
    private String province;
    // 城市
    private String city;
    // 地域编码
    @JsonProperty("addrcode")
    private String addrCode;
    // 浏览器
    private String browser;
    // 操作系统
    private String os;

    // 用户ID
    private String uid;

    // 取得source字段
    public String source() {

        if (this instanceof ReqLog)
            return "req";
        else if (this instanceof TrackLog)
            return "track";

        return null;

    }

    public void setTimestamp(long time) {
        this.timestamp1 = time;
        this.timestamp2 = new Date(time);
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getTimestamp1() {
        return timestamp1;
    }

    public void setTimestamp1(long timestamp1) {
        this.timestamp1 = timestamp1;
    }

    public Date getTimestamp2() {
        return timestamp2;
    }

    public void setTimestamp2(Date timestamp2) {
        this.timestamp2 = timestamp2;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getPageUri() {
        return pageUri;
    }

    public void setPageUri(String pageUri) {
        this.pageUri = pageUri;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddrCode() {
        return addrCode;
    }

    public void setAddrCode(String addrCode) {
        this.addrCode = addrCode;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
