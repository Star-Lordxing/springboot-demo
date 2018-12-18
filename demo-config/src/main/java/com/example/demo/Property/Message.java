package com.example.demo.Property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 王柱星
 * @version 1.0
 * @title
 * @time 2018年12月18日
 * @since 1.0
 */
@Component
public class Message{
    @Value("${code}")
    private String code;

    @Value("${content}")
    private String content;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
