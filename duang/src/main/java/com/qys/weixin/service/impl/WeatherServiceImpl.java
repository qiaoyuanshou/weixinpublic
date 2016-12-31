package com.qys.weixin.service.impl;

/**
 * Created with IntelliJ IDEA 2016.2.5
 * User: qiaoyuanshou
 * Date: 2016/12/6
 * Time: 14:09
 * To change this template use File | Settings | File Templates.
 */

import com.qys.weixin.bean.Weather;
import com.qys.weixin.constant.UrlConstant;
import com.qys.weixin.service.WeatherService;
import com.qys.weixin.util.HttpUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("weatherService")
public class WeatherServiceImpl implements WeatherService {


    public Weather weatherDetect(String place) {
        Weather weather = new Weather();
        //天气查询地址
        String queryUrl = UrlConstant.SENDPATH4;

        try {
            //对url进行编码
            queryUrl = queryUrl.replace("LOCATION", java.net.URLEncoder.encode(place, "UTF-8"));

            //调用天气查询接口
            String json = HttpUtil.getHttpGetRequest(queryUrl);
            //解析返回的json
            JSONObject jsonObj = JSONObject.fromObject(json);
            JSONArray results = jsonObj.getJSONArray("results");
            JSONObject resultsObject = (JSONObject) results.get(0);
            JSONArray index = resultsObject.getJSONArray("index");
            JSONArray weather_data = resultsObject.getJSONArray("weather_data");

            List<Map<String, String>> indexList = new ArrayList<Map<String, String>>();
            for (int i = 0; i < index.size(); i++) {
                JSONObject info = index.getJSONObject(i);
                Map<String, String> map = new HashMap<String, String>();
                map.put("title", info.getString("title"));
                map.put("zs", info.getString("zs"));
                map.put("tipt", info.getString("tipt"));
                map.put("des", info.getString("des"));

                indexList.add(map);
            }

            weather.setIndex(indexList);

            List<Map<String, String>> weather_dataList = new ArrayList<Map<String, String>>();
            for (int i = 0; i < weather_data.size(); i++) {
                JSONObject info = weather_data.getJSONObject(i);
                Map<String, String> map = new HashMap<String, String>();
                map.put("date", info.getString("date"));
                map.put("dayPictureUrl", info.getString("dayPictureUrl"));
                map.put("nightPictureUrl", info.getString("nightPictureUrl"));
                map.put("weather", info.getString("weather"));
                map.put("wind", info.getString("wind"));
                map.put("temperature", info.getString("temperature"));

                weather_dataList.add(map);
            }
            weather.setWeather_data(weather_dataList);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return weather;
    }

    public static void main(String[] args) {
        WeatherServiceImpl weatherService = new WeatherServiceImpl();
        weatherService.weatherDetect("长沙").getIndex();
        System.out.println(weatherService.weatherDetect("长沙").getIndex());
    }

}

