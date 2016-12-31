package com.qys.weixin.constant;

/**
 * Created with IntelliJ IDEA 2016.2.5
 * User: qiaoyuanshou
 * Date: 2016/12/6
 * Time: 14:07
 * To change this template use File | Settings | File Templates.
 */
public class UrlConstant {
    /**
     * 一些常用的链接
     */

    //天气查询
    public static final String SENDPATH4 = "http://api.map.baidu.com/telematics/v3/weather?location=LOCATION&output=json&ak=81218080E79C9685b35e757566d8cbe5";

    //热门影片
    public static final String SENDPATH5 = "http://api.map.baidu.com/telematics/v3/movie?qt=hot_movie&location=LOCATION&output=json&ak=81218080E79C9685b35e757566d8cbe5";

    //景点详情
    public static final String SENDPATH6 = "http://api.map.baidu.com/telematics/v3/travel_attractions?id=ID&output=json&ak=81218080E79C9685b35e757566d8cbe5";

    /**
     * 自己注册的图灵机器人
     * 每天5000个回复
     * 注册地址http://www.tuling123.com/member/robot/index.jhtml
     * key=1c86d0d7aca54c69b1f3f9e8a20089f8
     */
    public static final String TULING = "http://www.tuling123.com/openapi/api?key=1c86d0d7aca54c69b1f3f9e8a20089f8&info=";
    /**
     * 青云客智能聊天机器人API
     * <p>
     * http://api.qingyunke.com/
     */
    public static final String QINGYUNKE = "http://api.qingyunke.com/api.php?key=free&appid=0&msg=";

    /**
     * 菜单创建（POST） 限100（次/天）
      */
    public static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    /**
     * get方法删除菜单
     */
    public static String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

    /**
     * 获取accessCode
     */
    public static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APP_ID&secret=APP_SECRET";

    /**
     *
     * 地图坐标转换
     */
    public static String BAIDU_MAP_URL = "http://api.map.baidu.com/geocoder/v2/?output=json&ak=Q4WifKUp0jcx41ewoHUdy0CHiX9eAqWQ&location=Latitude,Longitude";

    /**
     * 微信精选
     */
    public static String WEIXIN_SELECTED_URL = "http://apis.baidu.com/showapi_open_bus/weixin/weixin_article_list?typeId=type_id&page=1&key=app_key";

    /**
     * 获取用户基本信息
     * APP_ACCESS_TOKEN
     * APP_OPENID
     */
    public static String USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=APP_ACCESS_TOKEN&openid=APP_OPENID&lang=zh_CN";


    /**
     * 获取关注用户
     */
    public static String GET_OPENIDS_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN_ID&next_openid=";

    /**
     * 这个地址是根据分组id来群发消息
     */
    public static String SEND_BY_GROUP_ID_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN_ID";
    /**
     * 这个地址是根据openid来群发消息
     */
    public static String SEND_BY_OPEN_ID_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN_ID";
}
