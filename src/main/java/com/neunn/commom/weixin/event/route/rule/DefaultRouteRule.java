package com.neunn.commom.weixin.event.route.rule;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.neunn.commom.weixin.event.handler.EventHandler;
import com.neunn.commom.weixin.event.route.rule.constant.Constant;
import com.neunn.commom.weixin.event.route.rule.exception.EventHandlerNotFound;
import com.neunn.commom.weixin.event.route.rule.exception.XMLFormatNotLegal;

public class DefaultRouteRule implements RouteRule {

  @Override
  public Map<String, String> getEventInfo(String xmlText) throws XMLFormatNotLegal {
    Map<String, String> eventInfo = null;
    ByteArrayInputStream bais = null;
    try {
        eventInfo = new HashMap<String, String>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
        bais = new ByteArrayInputStream(xmlText.getBytes());
        Document doc = dbBuilder.parse(bais);
        Node root = doc.getFirstChild();
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            eventInfo.put(node.getNodeName(), node.getTextContent());
        }
        if (!Constant.XML_NODE_MSG_TYPE_TEXT.equals(eventInfo.get(Constant.XML_NODE_MSG_TYPE))) {
            eventInfo.put(Constant.XML_NODE_EVENT, eventInfo.get(Constant.XML_NODE_MSG_TYPE));
        }
    } catch (DOMException e) {
        e.printStackTrace();
    } catch (ParserConfigurationException e) {
        e.printStackTrace();
    } catch (SAXException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (bais != null) {
            try {
                bais.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    return eventInfo;
  }

  @Override
  public Map<String, Object> handleEvent(Map<String, String> eventInfo, Map<String, EventHandler> eventHandlers) throws EventHandlerNotFound {
    String eventKey = eventInfo.get(Constant.XML_NODE_EVENT);
    if (eventKey != null) {
      eventKey = eventKey.toUpperCase();
    }
    EventHandler eventHandler = null;
    eventHandler = eventHandlers.get(eventKey);
    if (eventHandler == null) {
      throw new EventHandlerNotFound("EventHandler is not found.The eventKey is " + eventKey);
    } else {
      return eventHandler.handleEvent(eventInfo);
    }
  }

}
