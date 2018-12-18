package com.example.demo.Property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author 王柱星
 * @version 1.0
 * @title
 * @time 2018年12月18日
 * @since 1.0
 */
@Component
@Profile("devMsg")
@Primary
public class DevMsg extends BaseMsg{
    @Value("${msg.code}")
    private String msgCode;

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }
}
