package com.example.controller;


import com.alibaba.fastjson.JSON;
import com.example.POJO.Websocketmessage;
import com.example.coder.Socketencoder;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint(value = "/socket/{userid}",encoders = {Socketencoder.class})
@Component
public class WebSocket {
    String userid;
    private static ConcurrentHashMap<String, Session> connections = new ConcurrentHashMap<>();

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        System.out.println("客户端发送："+message);
        Websocketmessage wb = JSON.parseObject(message,Websocketmessage.class);
        send(this.userid,wb);

    }
    @OnOpen
    public void onOpen(Session session, @PathParam("userid")String id){
        connections.put(id,session);
        this.userid=id;
        System.out.println("用户"+id+"加入！");
    }

    @OnClose
    public void onClose(){
        connections.remove(this.userid);
        System.out.println("用户"+userid+"断开！");
    }
    public void send(String id, Websocketmessage message){
        Session session = connections.get(id);
        if(session!=null && session.isOpen()){
            session.getAsyncRemote().sendObject(message);
        }
    }


}
