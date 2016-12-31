package com.qys.weixin.entity.Message.resp;

/**
 * Created with IntelliJ IDEA 2016.2.5
 * User: qiaoyuanshou
 * Date: 2016/12/7
 * Time: 18:33
 * To change this template use File | Settings | File Templates.
 */
public class SendAllFiter {

    /**
     * 用于设定是否向全部用户发送，值为true或false，选择true该消息群发给所有用户，选择false可根据group_id发送给指定群组的用户
     */
    private  boolean is_to_all;
    /**
     * 群发到的分组的group_id，参加用户管理中用户分组接口，若is_to_all值为true，可不填写group_id
     */
    private  String group_id;


    public boolean is_to_all() {
        return is_to_all;
    }

    public void setIs_to_all(boolean is_to_all) {
        this.is_to_all = is_to_all;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }
}
