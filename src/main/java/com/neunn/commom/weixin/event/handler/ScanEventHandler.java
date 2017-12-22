package com.neunn.commom.weixin.event.handler;

import java.util.Map;

public class ScanEventHandler implements EventHandler {

  @Override
  public Map<String, Object> handleEvent(Map<String, String> eventInfo) {
    System.out.println("scan event invoked successfully.");
    return null;
  }

}
