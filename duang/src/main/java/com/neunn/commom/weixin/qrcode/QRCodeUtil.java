package com.neunn.commom.weixin.qrcode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.neunn.commom.weixin.http.HttpInterfaceRequester;
import com.neunn.commom.weixin.http.HttpInterfaceResponser;
import com.neunn.commom.weixin.qrcode.bean.QRCode;

public class QRCodeUtil {
	
	public static final String URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";
	
	public static final String QR_LIMIT_SCENE = "QR_LIMIT_SCENE";
	
	public static final String QR_LIMIT_STR_SCENE = "QR_LIMIT_STR_SCENE";
	
	public static final String QR_SCENE = "QR_SCENE";
	
	public static final String ACTION_NAME = "action_name";
	
	public static final String EXPIRE_SECONDS = "expire_seconds";
	
	public static final String ACTION_INFO = "action_info";
	
	public static final String SCENE = "scene";
	
	public static final String SCENE_ID = "scene_id";
	
	public static final String SCENE_STR = "scene_str";
	
	public static QRCode getPermanentStrQRCode(String token, String sceneStr) throws IOException{
		return getQRCode(token, QR_LIMIT_STR_SCENE, null, sceneStr, null);
	}
	
	public static QRCode getPermanentIntQRCode(String token, Integer sceneId) throws IOException{
		return getQRCode(token, QR_LIMIT_SCENE, sceneId, null, null);
	}
	
	public static QRCode getTempQRCode(String token, Integer sceneId) throws IOException{
		return getQRCode(token, QR_SCENE, sceneId, null, null);
	}
	
	public static QRCode getTempQRCode(String token, Integer sceneId, Integer expireSeconds) throws IOException {
	    return getQRCode(token, QR_SCENE, sceneId, null, expireSeconds);
	}
	
	private static QRCode getQRCode(String token, String actionName, Integer sceneId, String sceneStr, Integer expireSeconds) throws IOException{
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> actionInfo = new HashMap<String, Object>();
		Map<String, Object> scene = createScene(sceneId, sceneStr);
		if(scene == null){
			return null;
		}
		if(expireSeconds != null){
		    params.put(EXPIRE_SECONDS, expireSeconds);
		}
		actionInfo.put(SCENE, scene);
		params.put(ACTION_INFO, actionInfo);
		params.put(ACTION_NAME, actionName);
		HttpInterfaceRequester request = new HttpInterfaceRequester();
        HttpInterfaceResponser response = request.sendJson(URL+token, params, null);
        String content = response.getContent();
        if(content.indexOf("errcode") < 0){
            Gson gson = new Gson();
            return gson.fromJson(content, QRCode.class);
        }else{
            return null;
        }
	}
	
	private static Map<String, Object> createScene(Integer sceneId, String sceneStr){
		Map<String, Object> scene = new HashMap<String, Object>();
		if(sceneId == null && sceneStr == null){
			return null;
		}else if(sceneId != null){
			scene.put(SCENE_ID, sceneId);
		}else{
			scene.put(SCENE_STR, sceneStr);
		}
		return scene;
	}
}
