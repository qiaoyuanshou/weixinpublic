package com.qys.weixin.service.impl;

import com.qys.weixin.bean.Tuling;
import com.qys.weixin.constant.UrlConstant;
import com.qys.weixin.service.TulingService;
import com.qys.weixin.util.HttpUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA 2016.2.5
 * User: qiaoyuanshou
 * Date: 2016/12/6
 * Time: 17:54
 * To change this template use File | Settings | File Templates.
 */
@Service("tulingService")
public class TulingServiceImpl implements TulingService {

    @Override
    public Tuling getTulingRes(String inInfo) {

        Tuling tuling = new Tuling();
        //图灵查询返回结果
        String queryUrl = UrlConstant.TULING;

        if (inInfo == null) inInfo = "";

        try {
            queryUrl += inInfo;
            //调用图灵查询接口
            String json = HttpUtil.getHttpGetRequest(queryUrl);
            //解析返回的json
            JSONObject jsonObj = JSONObject.fromObject(json);
            String code = jsonObj.getString("code");
            String text = jsonObj.getString("text");
            if ("100000".equals(code)) tuling.setInfo(text);
        } catch (Exception e) {
            tuling.setInfo(inInfo);
            e.printStackTrace();
        }
        return tuling;
    }

    @Override
    public Tuling getQingYunKe(String inInfo) {
        Tuling tuling = new Tuling();
        //图灵查询返回结果
        String queryUrl = UrlConstant.QINGYUNKE;
        if (inInfo == null) inInfo = "";
        try {
            queryUrl += java.net.URLEncoder.encode(inInfo, "utf-8");

            //调用图灵查询接口
            String json = HttpUtil.getHttpGetRequest(queryUrl);
            //解析返回的json
            JSONObject jsonObj = JSONObject.fromObject(json);
            String result = jsonObj.getString("result");
            String content = jsonObj.getString("content");
            if ("0".equals(result)) tuling.setInfo(content);
        } catch (Exception e) {
            tuling.setInfo(inInfo);
            e.printStackTrace();
        }
        return tuling;
    }

    public static void main(String[] args) {
        TulingServiceImpl tulingService = new TulingServiceImpl();
        tulingService.getQingYunKe("笑话");

    }

}
