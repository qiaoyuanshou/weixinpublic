package com.neunn.commom.weixin.event.handler;

import java.util.HashMap;
import java.util.Map;

public class ClickEventHandler implements EventHandler {

  @Override
  public Map<String, Object> handleEvent(Map<String, String> eventInfo) {
    System.out.println("click event invoked successfully.");
    Map<String, Object> handleResult = new HashMap<String, Object>();
    handleResult.put("msg", "success.");
    return handleResult;
  }

}
