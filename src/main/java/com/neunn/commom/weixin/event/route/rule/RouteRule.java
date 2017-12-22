package com.neunn.commom.weixin.event.route.rule;

import java.util.Map;

import com.neunn.commom.weixin.event.handler.EventHandler;
import com.neunn.commom.weixin.event.route.rule.exception.EventHandlerNotFound;
import com.neunn.commom.weixin.event.route.rule.exception.XMLFormatNotLegal;

public interface RouteRule {
  
  public Map<String, String> getEventInfo(String xmlText) throws XMLFormatNotLegal;
  
  public Map<String, Object> handleEvent(Map<String, String> eventInfo, Map<String, EventHandler> eventHandlers) throws EventHandlerNotFound;
}
