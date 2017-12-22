package com.qys.weixin.batch;

import com.qys.weixin.constant.UrlConstant;
import com.qys.weixin.entity.Message.resp.SendAllFiter;
import com.qys.weixin.entity.Message.resp.SendAllText;
import com.qys.weixin.entity.Message.resp.SendAllTextMessageByGroup;
import com.qys.weixin.entity.Message.resp.SendAllTextMessageByOpenIds;
import com.qys.weixin.util.HttpUtil;
import com.qys.weixin.util.MenuUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA 2016.2.5
 * User: qiaoyuanshou
 * Date: 2016/12/7
 * Time: 10:54
 * To change this template use File | Settings | File Templates.
 */
public class WeiXinBatch {

    public void setMenu() {
        //业务逻辑
        MenuUtil menuUtil = new MenuUtil();
        String access_token = menuUtil.getAccessToken();
        // 这是一个符合菜单的json格式，“\”是转义符
        String jsonMenu = "{" +
                "    \"button\": [" +
                "        {" +
                "            \"name\": \"推送栏目\"," +
                "            \"sub_button\": [" +
                "                {" +
                "                    \"key\": \"11\"," +
                "                    \"name\": \"笑话大全\"," +
                "                    \"type\": \"click\"" +
                "                }," +
                "                {" +
                "                    \"key\": \"12\"," +
                "                    \"name\": \"深度美文\"," +
                "                    \"type\": \"click\"" +
                "                }" +
                "            ]" +
                "        }," +
                "        {" +
                "            \"name\": \"发送位置\"," +
                "            \"type\": \"location_select\"," +
                "            \"key\": \"rselfmenu_2_0\"" +
                "        }," +
                "        {" +
                "            \"name\": \"更多网站\"," +
                "            \"sub_button\": [" +
                "                {" +
                "                    \"type\": \"view\"," +
                "                    \"name\": \"淘宝\"," +
                "                    \"url\": \"http://taobao.com\"" +
                "                }," +
                "                {" +
                "                    \"name\": \"百度\"," +
                "                    \"type\": \"view\"," +
                "                    \"url\": \"http://m.baidu.com\"" +
                "                }" +
                "            ]" +
                "        }" +
                "    ]" +
                "}";
        menuUtil.DeleteMenu(access_token);
        menuUtil.CreateMenu(jsonMenu.replace(" ", ""), access_token);
    }

    public static void main(String[] args) {
    }

    /**
     * 根据openid群发
     */
    private static void sendMessageByOpenIds() {
        MenuUtil menuUtil = new MenuUtil();
        String access_token = menuUtil.getAccessToken();

        String resultJosn = HttpUtil.getHttpGetRequest(UrlConstant.GET_OPENIDS_URL.replace("ACCESS_TOKEN_ID", access_token));
        JSONObject jsonObj = JSONObject.fromObject(resultJosn);
        JSONArray openIds = jsonObj.getJSONObject("data").getJSONArray("openid");
        List<String> openIdsList = new ArrayList<String>();
        for (int i = 0; i < openIds.size(); i++) {
            String openid = (String) openIds.get(i);
            if (!StringUtils.isEmpty(openid)) openIdsList.add(openid);
        }
        SendAllText sendAllText = new SendAllText();
        sendAllText.setContent("这是一条按OpenIds群发测试消息~~");
        SendAllTextMessageByOpenIds sendAllTextMessageByOpenIds = new SendAllTextMessageByOpenIds();
        sendAllTextMessageByOpenIds.setText(sendAllText);
        sendAllTextMessageByOpenIds.setMsgtype("text");
        sendAllTextMessageByOpenIds.setTouser(openIdsList);
        String msg = JSONObject.fromObject(sendAllTextMessageByOpenIds).toString();
        System.out.println(msg);
        String sendByOpenIdUrl = UrlConstant.SEND_BY_OPEN_ID_URL.replace("ACCESS_TOKEN_ID", access_token);
        JSONObject jsonObject = HttpUtil.httpRequest(sendByOpenIdUrl, "POST", msg);
        System.out.println(jsonObject);
    }

    /**
     * 根据分组群发
     */
    private static void sendMessageByGroup() {

        MenuUtil menuUtil = new MenuUtil();
        String access_token = menuUtil.getAccessToken();
        SendAllText sendAllText = new SendAllText();
        sendAllText.setContent("这是一条群发测试消息~~");

        SendAllFiter sendAllFiter = new SendAllFiter();
        sendAllFiter.setGroup_id("1");
        sendAllFiter.setIs_to_all(true);

        SendAllTextMessageByGroup sendAllTextMessage = new SendAllTextMessageByGroup();
        sendAllTextMessage.setText(sendAllText);
        sendAllTextMessage.setMsgtype("text");
        sendAllTextMessage.setFilter(sendAllFiter);

        String msg = JSONObject.fromObject(sendAllTextMessage).toString().replace("_to_all", "is_to_all");
        String sendByGroupIdUrl = UrlConstant.SEND_BY_GROUP_ID_URL.replace("ACCESS_TOKEN_ID", access_token);
        JSONObject jsonObject = HttpUtil.httpRequest(sendByGroupIdUrl, "POST", msg);
        System.out.println(jsonObject);
    }

}
