package com.qys.weixin.bean;

/**
 * Created with IntelliJ IDEA 2016.2.5
 * User: qiaoyuanshou
 * Date: 2016/12/6
 * Time: 17:51
 * To change this template use File | Settings | File Templates.
 */
public class Tuling {
    private String info;//传入、返回信息
    private String code;//返回code

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static void main(String[] args) {
        String s = "String1";
        Tuling tuling = new Tuling();
        tuling.setCode("1");
        Long aLong = 01L;
        long kkk = 01L;
        System.out.printf("进入之前\n");
        System.out.printf("String:"+s+"\n");
        System.out.printf("bean:"+tuling.getCode()+"\n");
        System.out.printf("Long:"+aLong+"\n");
        System.out.printf("long:"+kkk+"\n");
        chuli(s,aLong,kkk,tuling);
        System.out.printf("进入之前\n");
        System.out.printf("String:"+s+"\n");
        System.out.printf("bean:"+tuling.getCode()+"\n");
        System.out.printf("Long:"+aLong+"\n");
        System.out.printf("long:"+kkk+"\n");
    }

    private static void chuli(String s, Long aLong, long kkk,Tuling tuling) {
        tuling.setCode("2");
        s="string2";
        aLong = 02l;
        kkk =02l;
    }
}
