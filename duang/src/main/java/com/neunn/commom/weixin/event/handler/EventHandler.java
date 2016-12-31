package com.neunn.commom.weixin.event.handler;

import java.util.Map;

public interface EventHandler {
  public Map<String, Object> handleEvent(Map<String, String> eventInfo);
}
