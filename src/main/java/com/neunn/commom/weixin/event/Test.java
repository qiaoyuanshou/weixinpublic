package com.neunn.commom.weixin.event;

import java.util.Map;
import java.util.Properties;

/*import com.neunn.commom.weixin.access_token.AccessTokenUtil;
import com.neunn.commom.weixin.access_token.bean.AccessToken;*/
import com.neunn.commom.weixin.event.route.EventRoute;
/*import com.neunn.commom.weixin.qrcode.QRCodeUtil;
import com.neunn.commom.weixin.qrcode.bean.QRCode;*/

public class Test {

  public static void main(String[] args) throws Exception {
    String xmlText = "<xml>"+
          "<ToUserName>zhangsan</ToUserName>"+
          "<FromUserName>lisi</FromUserName>"+
          "<CreateTime>123456789</CreateTime>"+
          "<MsgType>event</MsgType>"+
          "<Event>CLICK</Event>"+
          "<EventKey>abcdef</EventKey>"+
          "</xml>";
    Properties prop = new Properties();
    prop.setProperty("CLICK", "com.neunn.commom.weixin.event.handler.ClickEventHandler");
    prop.setProperty("SCAN", "com.neunn.commom.weixin.event.handler.ScanEventHandler");
    EventRoute eventRoute = new EventRoute(prop);
    Map<String, Object> handleResult = eventRoute.handleEvent(xmlText);
    System.out.println("The handle result is : " + handleResult);
    
    /*AccessToken accessToken = AccessTokenUtil.getAccessToken("wxcb6f2b6f96aff434", "63f52078b3d8063c9bf98b34ed6bfd58");
    System.out.println(accessToken.getAccess_token());
    QRCode qrCode = QRCodeUtil.getPermanentStrQRCode(accessToken.getAccess_token(), "123456");
    System.out.println(qrCode.getImgUrl());*/
  }
}
