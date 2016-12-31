package com.neunn.commom.weixin.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.neunn.commom.weixin.http.HttpInterfaceRequester;
import com.neunn.commom.weixin.http.HttpInterfaceResponser;

public class UserInfoUtil {

	public static final String ACCESS_TOKEN = "access_token";
	
	public static final String OPEN_ID = "openid";
	
	public static final String URL = "https://api.weixin.qq.com/cgi-bin/user/info";
	
	public static final String LANG = "lang";
	
	public static final String LANG_DEFAULT = "zh_CN";
	
	public static UserInfo getUserInfo(String accessToken, String openId) throws IOException{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ACCESS_TOKEN, accessToken);
		params.put(OPEN_ID, openId);
		params.put(LANG, LANG_DEFAULT);
		HttpInterfaceRequester request = new HttpInterfaceRequester();
		HttpInterfaceResponser response = request.sendGet(URL, params);
		String content = response.getContent();
		if(content.indexOf("errcode") < 0){
            Gson gson = new Gson();
            return gson.fromJson(content, UserInfo.class);
        }else{
            return null;
        }
	}
}
