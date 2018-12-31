package com.example.demo.controller;

import com.example.demo.Property.BaseMsg;
import com.example.demo.Property.Message;
import com.example.demo.bean.User;
import com.example.demo.kafka.Producer;
import com.example.demo.po.UserPO;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import test.config.SmsConfig;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Controller
@RequestMapping("/User")
@Api(description = "测试swagger注解的demo")
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
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HttpClient httpClient;

    @ApiOperation(value = "获取用户信息",notes = "返回单个用户信息")
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
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

    @ResponseBody
    @RequestMapping(value = "/getAllUser" ,method = RequestMethod.POST)
    @ApiOperation(value = "获取用户信息",notes = "返回单个用户信息")
    public List<UserPO> getAllUser(@ApiParam(required = false) @RequestBody User user) {
        userService.addUser();
        return userService.findAll();
    }

    @RequestMapping("/sendKafka")
    @ResponseBody
    public Object sendKafka(String msg){
        return producer.sendChannelMess("test-12",msg);
    }

    @RequestMapping("/testHttpClient")
    @ResponseBody
    public Object getUser(String msg) throws IOException {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet("http://192.168.1.100:8080/User/getAllUser");
        CloseableHttpResponse response = closeableHttpClient.execute(get);
        return EntityUtils.toString(response.getEntity(), "utf-8");
    }

    @RequestMapping("/testRestTemplate")
    @ResponseBody
    public Object testRestTemplate() throws IOException {
        ResponseEntity result= restTemplate.getForEntity("http://192.168.1.100:8080/User/getAllUser",ResponseEntity.class);
        return  restTemplate.postForLocation("http://192.168.1.100:8080/User/getAllUser",null);
    }


}
