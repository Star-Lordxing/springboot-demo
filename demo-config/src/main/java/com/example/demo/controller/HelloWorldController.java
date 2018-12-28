package com.example.demo.controller;

import com.example.demo.Property.BaseMsg;
import com.example.demo.Property.Message;
import com.example.demo.kafka.Producer;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import test.SmsConfig;

import java.util.Properties;

@Controller
@RequestMapping("/User")
public class HelloWorldController {

    @Autowired
    private BaseMsg msg;
    @Autowired
    private Message message;
    @Autowired
    private SmsConfig smsConfig;
    @Autowired
    private UserService userService;
    @Autowired
    private Producer producer;

    @RequestMapping("/getUser")
    public String getUser(@RequestParam("uid")Integer id, Model model) {
        System.out.println("id:"+id);
        model.addAttribute("userName","小明");
        model.addAttribute("phone","123456");
        return "user";
    }

    @RequestMapping("/hello")
    public String index() {
        System.out.println(msg.getMsgCode());

        //获取所有的属性
        Properties properties = System.getProperties();

        System.out.println(properties.getProperty("msg.msg"));

        //获取所有的属性
        System.out.println(System.getProperty("spring.profiles.active"));

        return "Hello World"+msg.getMsgCode();
    }

    @RequestMapping("/message")
    public String message() {
        System.out.println(message.getCode());
        return "message:"+message.getCode();
    }

    @RequestMapping("/smsConfig")
    @ResponseBody
    public String smsConfig() {
        userService.addUser1();
        System.out.println(smsConfig.getCode());
        return "message:"+ smsConfig.getCode();
    }

    @RequestMapping("/getAllUser")
    @ResponseBody
    public Object getAllUser() {
        userService.addUser();
        return userService.findAll();
    }

    @RequestMapping("/sendKafka")
    @ResponseBody
    public Object sendKafka(String msg){
        return producer.sendChannelMess("test-12",msg);
    }
}
