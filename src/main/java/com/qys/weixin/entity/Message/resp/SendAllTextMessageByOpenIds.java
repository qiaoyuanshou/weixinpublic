package com.qys.weixin.entity.Message.resp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA 2016.2.5
 * User: qiaoyuanshou
 * Date: 2016/12/7
 * Time: 18:25
 * To change this template use File | Settings | File Templates.
 */
public class SendAllTextMessageByOpenIds {

    /**
     * 群发给谁
     */

    List<String> touser = new ArrayList<String>();

    /**
     * 	群发的消息类型，
     * 	图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video，卡券为wxcard
     * 	暂时进行文本群发
     */
    private String msgtype;

    /**
     * 群发内容
     */
    private SendAllText text;

    public List<String> getTouser() {
        return touser;
    }

    public void setTouser(List<String> touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public SendAllText getText() {
        return text;
    }

    public void setText(SendAllText text) {
        this.text = text;
    }
}
