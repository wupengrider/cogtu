package com.cogtu.realtime.log;

import scala.Serializable;

/**
 * Created by lenovo on 2015/6/4.
 */
public class PicInfo implements Serializable {

    private String picId;

    private long size; // byte

    private long useTime; // mill sec--毫秒

    private int status; // 1表示下载成功

    private String errorCode;

    public boolean isPicDownloadSucc() {
        return status == 1; // && (errorCode == null || "".equals(errorCode));
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getUseTime() {
        return useTime;
    }

    public void setUseTime(long useTime) {
        this.useTime = useTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
