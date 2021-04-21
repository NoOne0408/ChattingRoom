package com.example.coder;

import com.alibaba.fastjson.JSON;
import com.example.POJO.Websocketmessage;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class Socketencoder implements Encoder.Text<Websocketmessage> {
    @Override
    public String encode(Websocketmessage websocketmessage) throws EncodeException {
        assert websocketmessage!=null;
        return JSON.toJSONString(websocketmessage);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
