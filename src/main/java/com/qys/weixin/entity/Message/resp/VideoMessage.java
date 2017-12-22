package com.qys.weixin.entity.Message.resp;

/**
 * Created with IntelliJ IDEA 2016.2.5
 * User: qiaoyuanshou
 * Date: 2016/12/5
 * Time: 18:19
 * To change this template use File | Settings | File Templates.
 */
public class VideoMessage extends BaseMessage {

    private Video video;

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
