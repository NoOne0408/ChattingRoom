package com.example.POJO;

/*enum Messagetype{
    openseesion,communication,groupcommunication,pub
}
*/
public class Websocketmessage implements java.io.Serializable {
    private String messagetype;
    private String touserid;
    private String openedsession;
    private String message;
    private String fromuserid;

    public String getMessagetype() {
        return messagetype;
    }

    @Override
    public String toString() {
        return "Websocketmessage{" +
                "messagetype='" + messagetype + '\'' +
                ", touserid='" + touserid + '\'' +
                ", openedsession='" + openedsession + '\'' +
                ", message='" + message + '\'' +
                ", fromuserid='" + fromuserid + '\'' +
                '}';
    }

    public String getTouserid() {
        return touserid;
    }

    public String getOpenedsession() {
        return openedsession;
    }

    public String getMessage() {
        return message;
    }

    public String getFromuserid() {
        return fromuserid;
    }

    public void setMessagetype(String messagetype) {
        this.messagetype = messagetype;
    }

    public void setTouserid(String touserid) {
        this.touserid = touserid;
    }

    public void setOpenedsession(String openedsession) {
        this.openedsession = openedsession;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setFromuserid(String fromuserid) {
        this.fromuserid = fromuserid;
    }
}
