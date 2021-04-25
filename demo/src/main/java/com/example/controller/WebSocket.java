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
        Websocketmessage swb = new Websocketmessage();
        //用户间通信
        switch (wb.getMessagetype()) {
            case "Communication":
                swb.setMessagetype(wb.getMessagetype());
                swb.setContexttype(wb.getContexttype());
                swb.setFromuserid(this.userid);
                swb.setMessage(wb.getMessage());
                //用户发送了一个文本消息
                switch (wb.getContexttype()) {
                    case ("Text"):
                        //将该消息传入数据库

                        break;
                    //用户发送了一个图片消息
                    case "File":
                        //将该消息存入数据库

                        break;
                    default:
                        swb = new Websocketmessage();
                        swb.setMessagetype("Error");
                        senderror(swb);
                        return;
                }
                send(wb.getTouserid(), swb);
                break;
            //群聊通信
            case "GroupCommunication":
                swb.setMessagetype(wb.getMessagetype());
                swb.setContexttype(wb.getContexttype());
                swb.setFromuserid(this.userid);
                swb.setMessage(wb.getMessage());
                switch (wb.getContexttype()) {
                    case "Text":
                        //将该消息传入数据库
                        break;
                    case "File":
                        //将该消息传入数据库
                        break;
                    default:
                        swb = new Websocketmessage();
                        swb.setMessagetype("Error");
                        senderror(swb);
                        return;
                }
                send(wb.getTouserid(), swb);
                break;
            //添加好友申请
            case "AddFriend":
                swb.setMessagetype(wb.getMessagetype());
                swb.setFromuserid(this.userid);
                send(wb.getTouserid(), swb);
                break;
            //加入群聊
            case "AddGroup":
                //将该消息存入数据库(?)
                swb.setMessagetype(wb.getMessagetype());
                swb.setFromuserid(this.userid);
                //从数据库中查的群聊的群主
                String id = "";
                send(id, swb);
                break;
            //创建群聊
            case "CreateGroup":
                //向数据库建立群聊
                send(this.userid, swb);
                break;
            //匿名匹配聊天
            case "UnNamedMatch":
                //调用函数（很麻烦）
                send(this.userid, swb);
                break;
            //话题匹配聊天
            case "TopicMatch":
                //调用函数（很麻烦）
                send(this.userid, swb);
                break;
            //公开身份
            case "Pub":
                swb.setMessagetype(wb.getMessagetype());
                swb.setFromuserid(this.userid);
                send(wb.getTouserid(), swb);

                break;
            //功能型消息
            case "Fuction":
                //添加好友成功响应
                switch (wb.getContexttype()) {
                    case "AddFriendSuccess":
                        //将该消息存入数据库

                        swb.setMessagetype(wb.getMessagetype());
                        swb.setContexttype(wb.getContexttype());
                        swb.setFromuserid(this.userid);
                        send(wb.getTouserid(), swb);
                        break;
                    //添加好友失败响应
                    case "AddFriendFailed":
                        //将该消息存入数据库（？）

                        swb.setMessagetype(wb.getMessagetype());
                        swb.setContexttype(wb.getContexttype());
                        swb.setFromuserid(this.userid);
                        send(wb.getTouserid(), swb);
                        break;
                    //入群成功响应
                    case "AddGroupSuccess":
                        //将该消息存入数据库

                        swb.setMessagetype(wb.getMessagetype());
                        swb.setContexttype(wb.getContexttype());
                        swb.setFromuserid(this.userid);
                        send(wb.getTouserid(), swb);
                        break;
                    //入群失败响应
                    case "AddGroupFailed":
                        //将该消息存入数据库（？）

                        swb.setMessagetype(wb.getMessagetype());
                        swb.setContexttype(wb.getContexttype());
                        swb.setFromuserid(wb.getFromuserid());
                        send(wb.getTouserid(), swb);
                        break;
                }
                break;
        }


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
    public void senderror(Websocketmessage message){
        Session session = connections.get(this.userid);
        session.getAsyncRemote().sendObject(message);
    }


}
