package com.qys.weixin.service.impl;

import com.qys.weixin.constant.UrlConstant;
import com.qys.weixin.service.LocationService;
import com.qys.weixin.util.HttpUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA 2016.2.5
 * User: qiaoyuanshou
 * Date: 2016/12/7
 * Time: 15:14
 * 经纬度转变为地址
 * To change this template use File | Settings | File Templates.
 */
@Service("locationService")
public class LocationServiceImpl implements LocationService {


    /**
     * Latitude	地理位置纬度
     * Longitude	地理位置经度
     *
     * @param Latitude
     * @param Longitude
     * @return
     */
    public String getLocationBySome(String Latitude, String Longitude) {

        String location = "";
        //位置查询地址
        String queryUrl = UrlConstant.BAIDU_MAP_URL;
        try {
            //对url进行编码
            queryUrl = queryUrl.replace("Latitude", Latitude).replace("Longitude", Longitude);
            //调用位置查询接口
            String json = HttpUtil.getHttpGetRequest(queryUrl);
            //解析返回的json
            JSONObject jsonObj = JSONObject.fromObject(json);
            JSONObject results = jsonObj.getJSONObject("result");

            String formatted_address = results.getString("formatted_address");
            String sematic_description = results.getString("sematic_description");
            location = "您当前位置为：" +
                    formatted_address + sematic_description
                    + "\n当前位置只有您能看到，请放心使用！";

        } catch (Exception e) {
            location = "当前位置不明！ \n查询时queryUrl=" + queryUrl + " \n Latitude=" + Latitude + "\nLongitude=" + Longitude;
        }
        return location;
    }

    public static void main(String[] args) {
        LocationServiceImpl weatherService = new LocationServiceImpl();
        System.out.println(weatherService.getLocationBySome("23.137466", "113.352425"));
    }
}
