package com.example.demo;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class RestTemplateTest {

    private CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

    @Test
    public void TestGet() throws IOException {
        HttpGet get = new HttpGet("http://192.168.1.100:8080/User/getAllUser");
        CloseableHttpResponse response = closeableHttpClient.execute(get);
        String str =  EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(str);
    }

}
