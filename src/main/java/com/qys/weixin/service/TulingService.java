package com.qys.weixin.service;

import com.qys.weixin.bean.Tuling;

/**
 * Created with IntelliJ IDEA 2016.2.5
 * User: qiaoyuanshou
 * Date: 2016/12/6
 * Time: 17:54
 * To change this template use File | Settings | File Templates.
 */
public interface TulingService {
    public Tuling getTulingRes(String inInfo);

    public Tuling getQingYunKe(String inInfo);
}
