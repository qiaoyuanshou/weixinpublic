package com.neunn.commom.weixin.oauth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.neunn.commom.weixin.http.HttpInterfaceRequester;
import com.neunn.commom.weixin.http.HttpInterfaceResponser;
import com.neunn.commom.weixin.oauth.bean.WeiXinAccessToken;
import com.neunn.commom.weixin.oauth.bean.WeiXinUserInfo;

public class OAuth2 {
    
    public static WeiXinAccessToken getAccessToken(String appId, String secret, String code) throws IOException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appid", appId);
        params.put("secret", secret);
        params.put("code", code);
        params.put("grant_type", "authorization_code");
        HttpInterfaceRequester request = new HttpInterfaceRequester();
        HttpInterfaceResponser response = request.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token", params);
        String content = response.getContent();
        if(content.indexOf("errcode") < 0){
            Gson gson = new Gson();
            return gson.fromJson(content, WeiXinAccessToken.class);
        }else{
            return null;
        }
    }
    
    public static WeiXinUserInfo getUserInfo(String appId, String secret, String code) throws IOException{
        WeiXinAccessToken accessToken = getAccessToken(appId, secret, code);
        if(accessToken != null){
            return getUserInfo(accessToken.getAccess_token(), accessToken.getOpenid());
        }else{
            return null;
        }
        
    }
    
    public static WeiXinUserInfo getUserInfo(String accessToken, String openid) throws IOException{
        if(checkAccessToken(accessToken, openid)){
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("access_token", accessToken);
            params.put("openid", openid);
            params.put("lang", "zh_CN");
            HttpInterfaceRequester request = new HttpInterfaceRequester();
            HttpInterfaceResponser response = request.sendGet("https://api.weixin.qq.com/sns/userinfo", params);
            String content = response.getContent();
            if(content.indexOf("errcode") < 0){
                Gson gson = new Gson();
                return gson.fromJson(content, WeiXinUserInfo.class);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
    
    public static Boolean checkAccessToken(String accessToken, String openid) throws IOException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("access_token", accessToken);
        params.put("openid", openid);
        HttpInterfaceRequester request = new HttpInterfaceRequester();
        HttpInterfaceResponser response = request.sendGet("https://api.weixin.qq.com/sns/auth", params);
        String content = response.getContent();
        return content.indexOf("ok") > 0;
    }
}
