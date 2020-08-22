package org.acme.kafkaconsumer;

import javax.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

import org.eclipse.microprofile.reactive.messaging.Incoming;


import java.util.Map;

import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;


/*
Receives Kafka message on atopic, outputs to Websocket, all connected browsers
*/

@ServerEndpoint("/endpoint")
@ApplicationScoped
public class MyStreamSubscriber {
    private static final Logger LOG = Logger.getLogger(MyStreamSubscriber.class);

    Map<String, Session> sessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session) {
      LOG.info("onOpen");
      LOG.info("onOpen ID: " + session.getId());    
      sessions.put(session.getId(),session);      
    }
  
    @OnClose
    public void onClose(Session session) {
        LOG.info("onClose");
        sessions.remove(session.getId());      
    }
  
    @OnError
    public void onError(Session session, Throwable throwable) {        
        LOG.error("onError", throwable);        
    }
  
    @OnMessage
    public void onMessage(String message, Session session) {      
      LOG.info("onMessage: " + message);
      LOG.info("onMessage ID:" + session.getId());
    }    
 
    @Incoming("mystream")
    public void process(String msg) {
        LOG.info("RECEIVED: " + msg);
        broadcast(msg);
    }     

    /*
    @Incoming("mystream")
    public CompletionStage<Void> process(KafkaMessage<String,String> msg) {
        LOG.info(msg.getPayload());
        return msg.ack();
    }
    */    

    public void broadcast(String message) {
      sessions.values().forEach(session -> {
        session.getAsyncRemote().sendObject(message, result ->  {
            if (result.getException() != null) {
                LOG.error("Unable to send message: " + result.getException());
            }
        });
      });
    }      

}