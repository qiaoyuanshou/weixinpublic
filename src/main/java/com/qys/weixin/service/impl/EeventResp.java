package com.qys.weixin.service.impl;

import com.qys.weixin.bean.Tuling;
import com.qys.weixin.constant.Constant;
import com.qys.weixin.constant.UrlConstant;
import com.qys.weixin.entity.Message.resp.Article;
import com.qys.weixin.entity.Message.resp.NewsMessage;
import com.qys.weixin.entity.Message.resp.TextMessage;
import com.qys.weixin.service.LocationService;
import com.qys.weixin.service.TulingService;
import com.qys.weixin.util.HttpUtil;
import com.qys.weixin.util.MenuUtil;
import com.qys.weixin.util.MessageUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA 2016.2.5
 * User: qiaoyuanshou
 * Date: 2016/12/7
 * Time: 14:28
 * To change this template use File | Settings | File Templates.
 */
@Service
public class EeventResp {


    @Resource
    private TulingService tulingService;

    @Resource
    private LocationService locationService;

    public String getContentResp(Map<String, String> requestMap, TextMessage textMessage, NewsMessage newsMessage, List<Article> articleList) {
        String respMessage = null;
        // 接收用户发送的文本消息内容
        String event = requestMap.get("Event").toLowerCase();
        if (Constant.EVENT_TYPE_SUBSCRIBE.equals(event)) {
            //关注推送
            String nikeName = getNikeName(requestMap.get("FromUserName"));
            textMessage.setContent("欢迎"+nikeName+"关注本测试微信号~  \n            --我是一条小尾巴");
            // 将文本消息对象转换成xml字符串
            respMessage = MessageUtil.textMessageToXml(textMessage);
        } else if ("click".equals(event)) {
            //点击事件
            String eventKey = requestMap.get("EventKey").toLowerCase();
            if ("11".equals(eventKey)) {
                Tuling tuling = tulingService.getQingYunKe("讲个笑话");
                String xiaohuaInfo=tuling.getInfo().replace("提示：按分类看笑话请发送“笑话分类”","");
                xiaohuaInfo = xiaohuaInfo.replace("{br}","\n");
                textMessage.setContent(xiaohuaInfo);
                respMessage = MessageUtil.textMessageToXml(textMessage);
            } else if ("12".equals(eventKey)) {
                getPerfect(articleList);
                // 设置图文消息个数
                newsMessage.setArticleCount(articleList.size());
                // 设置图文消息包含的图文集合
                newsMessage.setArticles(articleList);
                // 将图文消息对象转换成xml字符串
                respMessage = MessageUtil.newsMessageToXml(newsMessage);
            } else {
                textMessage.setContent("此功能正在开发，请稍后关注~");
                respMessage = MessageUtil.textMessageToXml(textMessage);
            }

        } else if ("view".equals(event)) {
            //跳转url
            String eventKey = requestMap.get("EventKey").toLowerCase();//推送时的url
            //TODO记录数据库 操作：用户进入了网址
        } else if (Constant.REQ_MESSAGE_TYPE_LOCATION.equals(event)) {
            //用户同意推送地址信息
            String latitude = requestMap.get("Latitude");
            String longitude = requestMap.get("Longitude");
            String location = locationService.getLocationBySome(latitude, longitude);
            textMessage.setContent(location);
            respMessage = MessageUtil.textMessageToXml(textMessage);
        } else {
            Tuling tuling = tulingService.getQingYunKe(event);
            textMessage.setContent(tuling.getInfo().replace("{br}","\n"));
            respMessage = MessageUtil.textMessageToXml(textMessage);
        }
        return respMessage;
    }

    /**
     * 获取用户信息
     * @param openId
     * @return
     */
    private String getNikeName(String openId) {
        try {
            MenuUtil menuUtil = new MenuUtil();
            String access_token = menuUtil.getAccessToken();
            String getUserInfoUrl = UrlConstant.USER_INFO_URL.replace("APP_ACCESS_TOKEN", access_token).replace("APP_OPENID", openId);
            String getUserInfo = HttpUtil.getHttpGetRequest(getUserInfoUrl);
            JSONObject jsonObject = JSONObject.fromObject(getUserInfo.replace(" ", ""));
            String location = jsonObject.getString("province")+jsonObject.getString("city");
            if (StringUtils.isEmpty(location)) return jsonObject.getString("nickname");
            return location+"的"+jsonObject.getString("nickname");
        }catch (Exception e){
            return "";
        }
    }

    public static void main(String[] args) {
        EeventResp eeventResp = new EeventResp();
        eeventResp.getNikeName("ofSkTv2wseNVt419UFYJhezUcvcc");
    }
    /***
     * 精彩推荐  调取接口获取
     * @param articleList
     */
    private void getPerfect(List<Article> articleList) {
        String result = HttpUtil.getWeiXinSelected();
        JSONObject jsonObj = JSONObject.fromObject(result);
        JSONObject showBody = jsonObj.getJSONObject("showapi_res_body");
        JSONObject pageBean = showBody.getJSONObject("pagebean");
        JSONArray contentList = pageBean.getJSONArray("contentlist");
        for (int i = 0; i < contentList.size() && i <= 5; i++) {
            JSONObject beanObject = contentList.getJSONObject(i);
            String url = beanObject.getString("url");
            String title = beanObject.getString("title");
            String contentImg = beanObject.getString("contentImg");
            String userName = beanObject.getString("userName");
            Article article = new Article();
            article.setTitle(title);
            article.setDescription("来自于：" + userName);
            article.setPicUrl(contentImg);
            article.setUrl(url);
            articleList.add(article);
        }
    }


}
