package com.qys.weixin.util;

/**
 * Created with IntelliJ IDEA 2016.2.5
 * User: qiaoyuanshou
 * Date: 2016/12/7
 * Time: 9:24
 * To change this template use File | Settings | File Templates.
 */

import com.qys.weixin.constant.Constant;
import com.qys.weixin.constant.UrlConstant;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

public class MenuUtil {


    public static Logger log = Logger.getLogger(MenuUtil.class);

    /**
     * 创建菜单
     *
     * @param jsonMenu    菜单的json格式
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public String CreateMenu(String jsonMenu, String accessToken) {
        String resultStr = "";
        // 调用接口获取token
        if (!StringUtils.isEmpty(accessToken)) {
            // 调用接口创建菜单
            int result = 0;
            // 拼装创建菜单的url
            String url = UrlConstant.MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
            // 调用接口创建菜单
            JSONObject jsonObject = HttpUtil.httpRequest(url, "POST", jsonMenu);
            log.info(jsonObject);

            if (null != jsonObject) {
                if (0 != jsonObject.getInt("errcode")) {
                    result = jsonObject.getInt("errcode");
                    log.error("创建菜单失败 errcode:" + jsonObject.getInt("errcode")
                            + "，errmsg:" + jsonObject.getString("errmsg"));
                }
            }
            // 判断菜单创建结果
            if (0 == result) {
                resultStr = "菜单创建成功";
                log.info(resultStr);
            } else {
                resultStr = "菜单创建失败，错误码：" + result;
                log.error(resultStr);
            }
        }

        return resultStr;
    }

    /**
     * 删除菜单
     *
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public String DeleteMenu(String accessToken) {
        String resultStr = "";
        // 调用接口获取token
        if (!StringUtils.isEmpty(accessToken)) {
            // 调用接口创建菜单
            // 拼装创建菜单的url
            String url = UrlConstant.MENU_DELETE_URL.replace("ACCESS_TOKEN", accessToken);
            // 调用接口创建菜单
            String result = HttpUtil.getHttpGetRequest(url);
            log.info(result);
        }
        return resultStr;
    }


    /**
     * 通过code换取网页授权access_token
     *
     * @param @return
     * @return String
     * @Title: getAccessToken
     */
    public String getAccessToken() {
        //获取到返回的参数
        String access_token ;
        try {
            //首先拿到微信公众号的AppID、AppSecret等参数
            String AppID = Constant.APPID;
            String AppSecret = Constant.APPSECRET;
            String url = UrlConstant.ACCESS_TOKEN_URL;
            /**
             *  2 第二步：通过code换取网页授权access_token
             */
            //用户授权，获取code
            url = url.replace("APP_ID", AppID).replace("APP_SECRET", AppSecret);
            String requestMethod = "GET";
            String outputStr = "";
            JSONObject httpRequest = HttpUtil.httpRequest(url, requestMethod, outputStr);
            access_token = httpRequest.getString("access_token");
            log.info("通过code获取取网页授权access_token={"+access_token+"}");

        } catch (Exception e) {
            log.info("===获取access_token出错===");
            access_token = "";
        }
        return access_token;
    }

//    public static void main(String[] args) {
//        MenuUtil menuUtil = new MenuUtil();
//        String accessToken= menuUtil.getAccessToken();
//        log.info(accessToken);
//        String request= HttpUtil.getHttpGetRequest();
//        log.info(request);
//    }

}
