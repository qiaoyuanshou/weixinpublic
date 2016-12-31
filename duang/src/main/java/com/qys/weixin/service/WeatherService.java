package com.qys.weixin.service;

import com.qys.weixin.bean.Weather;

/**
 * Created with IntelliJ IDEA 2016.2.5
 * User: qiaoyuanshou
 * Date: 2016/12/6
 * Time: 14:09
 * To change this template use File | Settings | File Templates.
 */
public interface WeatherService {
    public Weather weatherDetect(String place);
}
