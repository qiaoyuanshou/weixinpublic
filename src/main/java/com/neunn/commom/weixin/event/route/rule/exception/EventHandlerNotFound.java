package com.neunn.commom.weixin.event.route.rule.exception;

public class EventHandlerNotFound extends Exception {

  private static final long serialVersionUID = -3452877847279101619L;

  public EventHandlerNotFound() {
    super();
  }

  public EventHandlerNotFound(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

  public EventHandlerNotFound(String arg0) {
    super(arg0);
  }

  public EventHandlerNotFound(Throwable arg0) {
    super(arg0);
  }
}
