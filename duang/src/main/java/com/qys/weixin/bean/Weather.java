package com.qys.weixin.bean;
/**
 * Created with IntelliJ IDEA 2016.2.5
 * User: qiaoyuanshou
 * Date: 2016/12/6
 * Time: 14:03
 * To change this template use File | Settings | File Templates.
 */

import java.util.List;
import java.util.Map;

public class Weather {
    private String currentCity;//当前城市
    private List<Map<String, String>> index;//返回json中的index数组
    private List<Map<String, String>> weather_data;//返回的json数据中的weather_data数组

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public List<Map<String, String>> getIndex() {
        return index;
    }

    public void setIndex(List<Map<String, String>> index) {
        this.index = index;
    }

    public List<Map<String, String>> getWeather_data() {
        return weather_data;
    }

    public void setWeather_data(List<Map<String, String>> weather_data) {
        this.weather_data = weather_data;
    }


}