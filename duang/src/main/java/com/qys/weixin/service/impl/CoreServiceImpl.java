
package com.qys.weixin.service.impl;

import com.qys.weixin.constant.Constant;
import com.qys.weixin.entity.Message.resp.Article;
import com.qys.weixin.entity.Message.resp.NewsMessage;
import com.qys.weixin.entity.Message.resp.TextMessage;
import com.qys.weixin.service.CoreService;
import com.qys.weixin.util.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("coreService")
public class CoreServiceImpl implements CoreService {

    public static Logger log = Logger.getLogger(CoreServiceImpl.class);

    @Resource
    ContentResp contentResp;

    @Resource
    EeventResp eeventResp;

    @Override
    public String processRequest(HttpServletRequest request) {
        String respMessage = null;
        try {
            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");
            // 消息类型
            String CreateTime = requestMap.get("CreateTime");

            TextMessage textMessage = getTextMessage(fromUserName, toUserName, CreateTime);//文字回复
            NewsMessage newsMessage = getNewsMessage(fromUserName, toUserName);//图文回复
            List<Article> articleList = new ArrayList<Article>();

            if (msgType.equals(Constant.REQ_MESSAGE_TYPE_TEXT)) {
                // 文本消息
                respMessage = contentResp.getContentResp(requestMap, textMessage, newsMessage, articleList);
            } else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_EVENT)) {
                // 推送事件
                respMessage = eeventResp.getContentResp(requestMap, textMessage, newsMessage, articleList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return respMessage;
    }


    private NewsMessage getNewsMessage(String fromUserName, String toUserName) {
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(Constant.RESP_MESSAGE_TYPE_NEWS);
        newsMessage.setFuncFlag(0);
        return newsMessage;
    }

    private TextMessage getTextMessage(String fromUserName, String toUserName, String createTime) {
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setMsgType(Constant.RESP_MESSAGE_TYPE_TEXT);
        textMessage.setFuncFlag(0);
        return textMessage;
    }


}

