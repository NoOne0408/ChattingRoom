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
        if(wb.getMessagetype().equals("Communication")){
            swb.setMessagetype(wb.getMessagetype());
            swb.setContexttype(wb.getContexttype());
            swb.setFromuserid(this.userid);
            swb.setMessage(wb.getMessage());
            //用户发送了一个文本消息
            if(wb.getContexttype().equals(("Text"))){
                //将该消息传入数据库

            }
            //用户发送了一个图片消息
            else if(wb.getContexttype().equals("File")){
                //将该消息存入数据库

            }
            else{
                swb = new Websocketmessage();
                swb.setMessagetype("Error");
                senderror(swb);
                return;
            }
            send(wb.getTouserid(),swb);
        }
        //群聊通信
        else if (wb.getMessagetype().equals("GroupCommunication")){
            swb.setMessagetype(wb.getMessagetype());
            swb.setContexttype(wb.getContexttype());
            swb.setFromuserid(this.userid);
            swb.setMessage(wb.getMessage());
            if(wb.getContexttype().equals("Text")){
                //将该消息传入数据库
            }
            else if(wb.getContexttype().equals("File")){
                //将该消息传入数据库
            }
            else{
                swb=new Websocketmessage();
                swb.setMessagetype("Error");
                senderror(swb);
                return;
            }
            send(wb.getTouserid(),swb);
        }
        //添加好友申请
        else if (wb.getMessagetype().equals("AddFriend")){
            swb.setMessagetype(wb.getMessagetype());
            swb.setFromuserid(this.userid);
            send(wb.getTouserid(),swb);
        }
        //加入群聊
        else if(wb.getMessagetype().equals("AddGroup")){
            //将该消息存入数据库(?)
            swb.setMessagetype(wb.getMessagetype());
            swb.setFromuserid(this.userid);
            //从数据库中查的群聊的群主
            String id="";
            send(id,swb);
        }
        //创建群聊
        else if (wb.getMessagetype().equals("CreateGroup")){
            //向数据库建立群聊
            send(this.userid,swb);
        }
        //匿名匹配聊天
        else if (wb.getMessagetype().equals("UnNamedMatch")){
            //调用函数（很麻烦）
            send(this.userid,swb);
        }
        //话题匹配聊天
        else if (wb.getMessagetype().equals("TopicMatch")){
            //调用函数（很麻烦）
            send(this.userid,swb);
        }
        //公开身份
        else if (wb.getMessagetype().equals("Pub")){
            swb.setMessagetype(wb.getMessagetype());
            swb.setFromuserid(this.userid);
            send(wb.getTouserid(),swb);

        }
        //功能型消息
        else if (wb.getMessagetype().equals("Fuction")){
            //添加好友成功响应
            if (wb.getContexttype().equals("AddFriendSuccess")){
                //将该消息存入数据库

                swb.setMessagetype(wb.getMessagetype());
                swb.setContexttype(wb.getContexttype());
                swb.setFromuserid(this.userid);
                send(wb.getTouserid(),swb);
            }
            //添加好友失败响应
            else if (wb.getContexttype().equals("AddFriendFailed")){
                //将该消息存入数据库（？）

                swb.setMessagetype(wb.getMessagetype());
                swb.setContexttype(wb.getContexttype());
                swb.setFromuserid(this.userid);
                send(wb.getTouserid(),swb);
            }
            //入群成功响应
            else if(wb.getContexttype().equals("AddGroupSuccess")){
                //将该消息存入数据库

                swb.setMessagetype(wb.getMessagetype());
                swb.setContexttype(wb.getContexttype());
                swb.setFromuserid(this.userid);
                send(wb.getTouserid(),swb);
            }
            //入群失败响应
            else if (wb.getContexttype().equals("AddGroupFailed")){
                //将该消息存入数据库（？）

                swb.setMessagetype(wb.getMessagetype());
                swb.setContexttype(wb.getContexttype());
                swb.setFromuserid(wb.getFromuserid());
                send(wb.getTouserid(),swb);
            }
        }
        else{
            swb.setMessage("前端已经接受"+wb.getMessagetype());
            send(this.userid,swb);
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
