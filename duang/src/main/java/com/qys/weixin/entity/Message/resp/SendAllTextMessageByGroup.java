package com.qys.weixin.entity.Message.resp;

/**
 * Created with IntelliJ IDEA 2016.2.5
 * User: qiaoyuanshou
 * Date: 2016/12/7
 * Time: 18:25
 * To change this template use File | Settings | File Templates.
 */
public class SendAllTextMessageByGroup {

    /**
     * //是否群发 还是分组发
     */
    private SendAllFiter filter;


    /**
     * 	群发的消息类型，
     * 	图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video，卡券为wxcard
     */
    private String msgtype;

    /**
     * 群发内容
     */
    private SendAllText text;

    public SendAllFiter getFilter() {
        return filter;
    }

    public void setFilter(SendAllFiter filter) {
        this.filter = filter;
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
