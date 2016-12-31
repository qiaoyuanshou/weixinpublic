package com.qys.weixin.service.impl;

import com.qys.weixin.bean.Tuling;
import com.qys.weixin.entity.Message.resp.Article;
import com.qys.weixin.entity.Message.resp.NewsMessage;
import com.qys.weixin.entity.Message.resp.TextMessage;
import com.qys.weixin.service.TulingService;
import com.qys.weixin.util.MessageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA 2016.2.5
 * User: qiaoyuanshou
 * Date: 2016/12/7
 * Time: 14:09
 * 回复
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ContentResp {


    @Resource
    private TulingService tulingService;

    public String getContentResp(Map<String, String> requestMap, TextMessage textMessage, NewsMessage newsMessage, List<Article> articleList) {
        String respMessage;
        // 接收用户发送的文本消息内容
        String content = requestMap.get("Content");
        if ("1".equals(content)) {
            textMessage.setContent("Duang的一声~");
            // 将文本消息对象转换成xml字符串
            respMessage = MessageUtil.textMessageToXml(textMessage);
        } else if ("2".equals(content)) {
            articleList.add(getOneArticle());
            // 设置图文消息个数
            newsMessage.setArticleCount(articleList.size());
            // 设置图文消息包含的图文集合
            newsMessage.setArticles(articleList);
            // 将图文消息对象转换成xml字符串
            respMessage = MessageUtil.newsMessageToXml(newsMessage);
        } else if ("3".equals(content)) {
            articleList.add(getTwoArticle());
            articleList.add(getThreeArticle());
            articleList.add(getFourArticle());
            // 设置图文消息个数
            newsMessage.setArticleCount(articleList.size());
            // 设置图文消息包含的图文集合
            newsMessage.setArticles(articleList);
            // 将图文消息对象转换成xml字符串
            respMessage = MessageUtil.newsMessageToXml(newsMessage);
        } else {
            Tuling tuling = tulingService.getQingYunKe(content);
            textMessage.setContent(tuling.getInfo().replace("{br}","\n"));
            respMessage = MessageUtil.textMessageToXml(textMessage);
        }
        return respMessage;
    }

    private Article getFourArticle() {
        Article article3 = new Article();
        article3.setTitle("度娘");
        article3.setDescription("大帝吧~");
        article3.setPicUrl("http://www.qqtn.com/up/2014-5/14010748214962736.gif");
        article3.setUrl("https://tieba.baidu.com/f?kw=%C0%EE%D2%E3&fr=ala0&tpl=5&red_tag=0357352047&pn=0&");
        return article3;
    }

    private Article getThreeArticle() {
        Article article2 = new Article();
        article2.setTitle("评测项目");
        article2.setDescription("评测首页");
        article2.setPicUrl("http://img30.360buyimg.com/cf/jfs/t3163/357/3175115412/446157/9a1b48e2/57edbc1cN60822e4d.jpg");
        article2.setUrl("http://pingce.jd.com");
        return article2;
    }

    private Article getTwoArticle() {
        Article article1 = new Article();
        article1.setTitle("我是一条尖货多图文消息~~");
        article1.setDescription("尖货首页~");
        article1.setPicUrl("http://img30.360buyimg.com/cf/jfs/t3307/261/2990924347/131022/de93b6fe/57eb6761N57930ebc.jpg");
        article1.setUrl("http://zan.jd.com");
        return article1;
    }

    private Article getOneArticle() {
        Article article = new Article();
        article.setTitle("推送一条消息");
        article.setDescription("一个神奇的视频~");
        article.setPicUrl("http://i1.hdslb.com/video/2b/2b56c54efcb06910e443b6e7ad024359.jpg");
        article.setUrl("http://www.bilibili.com/video/av7048247/");
        return article;
    }
}
