package com.example.POJO;

/*enum Messagetype{
    Communication           用户间聊天
    GroupCommunication      群聊
    Pub                     匿名聊天公开身份
                            动态
    Success                 传输成功返回消息
    Error                   传输失败返回消息
    AddFriend               添加好友
    AddGroup                加入群聊
    CreateGroup             创建群聊
    Function                功能型消息
    UnNamedMatch            匿名聊天申请
    TopicMatch              话题匹配
}
*/

//contexttype用区分传送信息是文字还是文件，依次为text,file

/*enum contexttype{
    Text                    通信时传输文本
    File                    通信时传输图片
    AddFriendSuccess        申请添加好友成功
    AddGroupSuccess         申请加入群聊成功
    CreateGroupSuccess      创建群聊
    MatchSuccess            匹配成功
}
*/

public class Websocketmessage implements java.io.Serializable {
    private String messagetype;
    private String touserid;
    private String message;
    private String fromuserid;
    private String contexttype;

    @Override
    public String toString() {
        return "Websocketmessage{" +
                "messagetype='" + messagetype + '\'' +
                ", touserid='" + touserid + '\'' +
                ", message='" + message + '\'' +
                ", fromuserid='" + fromuserid + '\'' +
                ", contexttype='" + contexttype + '\'' +
                '}';
    }

    public String getMessagetype() {
        return messagetype;
    }

    public void setMessagetype(String messagetype) {
        this.messagetype = messagetype;
    }

    public String getTouserid() {
        return touserid;
    }

    public void setTouserid(String touserid) {
        this.touserid = touserid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFromuserid() {
        return fromuserid;
    }

    public void setFromuserid(String fromuserid) {
        this.fromuserid = fromuserid;
    }

    public String getContexttype() {
        return contexttype;
    }

    public void setContexttype(String contexttype) {
        this.contexttype = contexttype;
    }
}
