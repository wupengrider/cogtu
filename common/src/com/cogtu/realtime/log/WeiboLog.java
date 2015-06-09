package com.cogtu.realtime.log;

import scala.Serializable;

/**
 * Created by lenovo on 2015/6/5.
 */
public class WeiboLog implements Serializable {

    private int isDownload = -1;

    private String agent;

    private String status;

    private String errorCode;

    public int getIsDownload() {
        return isDownload;
    }

    public void setIsDownload(int isDownload) {
        this.isDownload = isDownload;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
