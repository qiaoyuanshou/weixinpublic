package com.neunn.commom.weixin.access_token;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.neunn.commom.weixin.access_token.bean.AccessToken;
import com.neunn.commom.weixin.http.HttpInterfaceRequester;
import com.neunn.commom.weixin.http.HttpInterfaceResponser;

public class AccessTokenUtil {
	
	public static final String URL = "https://api.weixin.qq.com/cgi-bin/token";
	
	public static final String GRANT_TYPE = "grant_type";
	
	public static final String CLIENT_CREDENTIAL = "client_credential";
	
	public static final String APPID = "appid";
	
	public static final String SECRET = "secret";
	
	public static AccessToken getAccessToken(String appid, String secret) throws IOException{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(GRANT_TYPE, CLIENT_CREDENTIAL);
		params.put(APPID, appid);
		params.put(SECRET, secret);
		HttpInterfaceRequester request = new HttpInterfaceRequester();
        HttpInterfaceResponser response = request.sendGet(URL, params);
        String content = response.getContent();
        if(content.indexOf("errcode") < 0){
            Gson gson = new Gson();
            return gson.fromJson(content, AccessToken.class);
        }else{
            return null;
        }
	}
}
