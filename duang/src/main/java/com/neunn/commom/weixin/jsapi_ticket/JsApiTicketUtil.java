package com.neunn.commom.weixin.jsapi_ticket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.neunn.commom.weixin.http.HttpInterfaceRequester;
import com.neunn.commom.weixin.http.HttpInterfaceResponser;

public class JsApiTicketUtil {
	
	public static final String URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
	
	public static final String ACCESS_TOKEN = "access_token";
	
	public static final String TYPE = "type";
	
	public static final String JS_API = "jsapi";
	
	public static JsApiTicket getjsApiTicket(String accessToken) throws IOException{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ACCESS_TOKEN, accessToken);
		params.put(TYPE, JS_API);
		HttpInterfaceRequester request = new HttpInterfaceRequester();
        HttpInterfaceResponser response = request.sendGet(URL, params);
        String content = response.getContent();
        if(content.indexOf("ok") > 0){
            Gson gson = new Gson();
            return gson.fromJson(content, JsApiTicket.class);
        }else{
            return null;
        }
	}
}
