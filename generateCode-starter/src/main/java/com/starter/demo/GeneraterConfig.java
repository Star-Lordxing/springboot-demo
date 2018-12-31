package com.starter.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneraterConfig {

    @Bean
    GeneraterIdService getGeneraterIdService(){
        return new GeneraterIdService();
    }

}
