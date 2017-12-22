package com.neunn.commom.weixin.menu;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neunn.commom.weixin.http.HttpInterfaceRequester;
import com.neunn.commom.weixin.http.HttpInterfaceResponser;

public class MenuUtil {
	
	private static String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
	
	/**
	 * 发布菜单
	 * @param accessToken	ACCESS_TOKEN
	 * @param menus			菜单集合
	 */
	public static boolean publishMenu(String accessToken, List<Menu> menus) throws IOException{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("button", menus);
		HttpInterfaceRequester request = new HttpInterfaceRequester();
        HttpInterfaceResponser response = request.sendJson(url + accessToken, params, null);
        String content = response.getContent();
        System.out.println(content);
        if(content.indexOf("ok") > 0){
            return true;
        }else{
            return false;
        }
	}
}
