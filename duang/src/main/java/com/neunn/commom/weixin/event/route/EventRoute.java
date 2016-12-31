package com.neunn.commom.weixin.event.route;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.neunn.commom.weixin.event.handler.EventHandler;
import com.neunn.commom.weixin.event.route.rule.DefaultRouteRule;
import com.neunn.commom.weixin.event.route.rule.RouteRule;
import com.neunn.commom.weixin.event.route.rule.exception.EventHandlerNotFound;
import com.neunn.commom.weixin.event.route.rule.exception.XMLFormatNotLegal;

public class EventRoute {
  private RouteRule routeRule = null;
  private Map<String, EventHandler> eventHandlers = null;

  public EventRoute(Properties prop) {
    this(null, prop);
  }
  
  public EventRoute(Map<String, EventHandler> eventHandlers) {
    this(null, eventHandlers);
  }
  
  public EventRoute(RouteRule routeRule, Map<String, EventHandler> eventHandlers) {
    if (routeRule == null) {
      this.routeRule = new DefaultRouteRule();
    } else {
      this.routeRule = routeRule;
    }
    this.eventHandlers = eventHandlers;
  }
  
  public EventRoute(RouteRule routeRule, Properties prop) {
    if (routeRule == null) {
      this.routeRule = new DefaultRouteRule();
    } else {
      this.routeRule = routeRule;
    }
    //初始化所有的事件处理Handler放到一个Map容器中
    eventHandlers = new HashMap<String, EventHandler>();
    Enumeration<?> enumeration = prop.propertyNames();
    while (enumeration.hasMoreElements()) {
      String key = (String)enumeration.nextElement();
      String className = prop.getProperty(key);
      EventHandler eventHandler = this.instanceEventHandler(className);
      eventHandlers.put(key, eventHandler);
    }
  }

  public Map<String, Object> handleEvent(String xmlText) throws XMLFormatNotLegal, EventHandlerNotFound {
    Map<String, String> eventInfo = routeRule.getEventInfo(xmlText);
    return routeRule.handleEvent(eventInfo, eventHandlers);
  }
  
  /**
   * 实例化事件处理Handler
   * @param className
   * @return eventHandler
   */
  private EventHandler instanceEventHandler(String className) {
    EventHandler eventHandler = null;
    try {
      Class<?> classObj = Class.forName(className);
      eventHandler = (EventHandler)classObj.newInstance();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return eventHandler;
  }
}
