package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import test.config.SmsConfig;

@SpringBootApplication
@Import({SmsConfig.class})
//@MapperScan("com.example.demo.dao")
// @EnableScheduling    // 启用间隔调度

//@Configuration("SwaggerConfig.class")
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

