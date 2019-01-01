package com.example.demo;

import com.example.demo.controller.HelloWorldController;
import com.example.demo.dao.UserDao;
import com.example.demo.po.UserPO;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.thymeleaf.spring5.context.SpringContextUtils;

import javax.annotation.Resource;
import javax.sql.DataSource;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class DemoApplicationTests {

	private MockMvc mvc;
	@Resource
	private DataSource dataSource;
	@Resource
	private UserDao userDao;
	@Resource
	private UserService userService;
	@Autowired
	ApplicationContext applicationContext;


	@Before
	public void setUp(){
		mvc = MockMvcBuilders.standaloneSetup(new HelloWorldController()).build();
	}

	@Test
	public void getHello() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Hello World")));
	}

	@Test
	public void testConnection() throws Exception {
		System.out.println(this.dataSource.getConnection());
	}

	@Test
	public void getAllUser(){
		System.out.println(userDao.findAll());
	}

	@Test
	public void getAllUserByPage(){
		PageHelper.startPage(1,2);
		System.out.println(userDao.findAll());
	}

	@Test
	public void addUser(){
		RedisTemplate redisTemplate1 = (RedisTemplate)applicationContext.getBean("redisTemplate");
		System.out.println(redisTemplate1.opsForValue().get("xing"));
		//userService.addUser();
	}

	@Resource
	private JavaMailSender javaMailSender ;
	@Test
	public void testSendMail() {
		SimpleMailMessage message = new SimpleMailMessage() ;    // 要发送的消息内容
		message.setFrom("18842688753@163.com");
		message.setTo("1065754909@qq.com");
		message.setSubject("测试邮件）");
		message.setText("好好学习，天天向上");
		this.javaMailSender.send(message);
	}

}

