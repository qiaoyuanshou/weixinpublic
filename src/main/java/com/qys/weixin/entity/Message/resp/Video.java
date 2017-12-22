package com.qys.weixin.entity.Message.resp;

/**
 * Created with IntelliJ IDEA 2016.2.5
 * User: qiaoyuanshou
 * Date: 2016/12/5
 * Time: 18:17
 * 视频
 * To change this template use File | Settings | File Templates.
 */
public class Video {

    private String mediaId;//视频id
    private String title;//视频标题
    private String description;//视频描述

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
