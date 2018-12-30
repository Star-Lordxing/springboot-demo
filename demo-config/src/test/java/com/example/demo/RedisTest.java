package com.example.demo;

import com.example.demo.bean.User;
import com.example.demo.po.UserPO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class RedisTest {
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource(name = "redisTwo")
    private RedisTemplate<String, Object> redisTemplate2;

    @Test
    public void testRedisString(){
        redisTemplate.opsForValue().set("xing","12345678");
        System.out.println(redisTemplate.opsForValue().get("xing"));
    }

    @Test
    public void testRedis(){
        UserPO userPO = new UserPO("秀明",12);

        redisTemplate.opsForValue().set("user1",userPO);
        UserPO result = (UserPO) redisTemplate.opsForValue().get("user1");
        System.out.println(result);
    }

    @Test
    public void testStringsRedis(){
        UserPO userPO = new UserPO("小明",14);

       /* redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(UserPO.class));
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(UserPO.class));*/
        redisTemplate.opsForValue().set("user-3",userPO);
        redisTemplate.opsForHash().put("user-6","user-7",userPO);
        //UserPO result = (UserPO) stringRedisTemplate.opsForValue().get("user1");
        UserPO result = (UserPO) redisTemplate.opsForHash().get("user-6","user-7");
        System.out.println(result);
    }

    @Test
    public void testStringRedis(){
        stringRedisTemplate.opsForValue().set("test_1","我是小明");
        redisTemplate.opsForValue().set("test_2","我是小明");
    }

    @Test
    public void testRedis2(){
        UserPO userPO = new UserPO("小明",25);

        redisTemplate2.opsForValue().set("user15",userPO);
        UserPO result = (UserPO) redisTemplate2.opsForValue().get("user15");
        System.out.println(result);
    }

    @Test
    public void testRedis1(){
        User user = new User();
        user.setAge(11);
        user.setName("我是小王1");
        redisTemplate.opsForValue().set("user37",user);
        System.out.println(redisTemplate.getValueSerializer());
        System.out.println(redisTemplate.getKeySerializer());
        User result = (User) redisTemplate.opsForValue().get("user37");
        System.out.println(result);
    }

    @Test
    public void testSerial(){
        UserPO userPO = new UserPO(1111L,"小明_testRedis1",25);
        List<Object> list = new ArrayList<>();
        for(int i=0;i<200;i++){
            list.add(userPO);
        }
        JdkSerializationRedisSerializer j = new JdkSerializationRedisSerializer();
        GenericJackson2JsonRedisSerializer g = new GenericJackson2JsonRedisSerializer();
        Jackson2JsonRedisSerializer j2 = new Jackson2JsonRedisSerializer(List.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        j2.setObjectMapper(objectMapper);

        Long j_s_start = System.currentTimeMillis();
        byte[] bytesJ = j.serialize(list);
        System.out.println("JdkSerializationRedisSerializer序列化时间："+(System.currentTimeMillis()-j_s_start) + "ms,序列化后的长度：" + bytesJ.length);
        Long j_d_start = System.currentTimeMillis();
        j.deserialize(bytesJ);
        System.out.println("JdkSerializationRedisSerializer反序列化时间："+(System.currentTimeMillis()-j_d_start));


        Long g_s_start = System.currentTimeMillis();
        byte[] bytesG = g.serialize(list);
        System.out.println("GenericJackson2JsonRedisSerializer序列化时间："+(System.currentTimeMillis()-g_s_start) + "ms,序列化后的长度：" + bytesG.length);
        Long g_d_start = System.currentTimeMillis();
        g.deserialize(bytesG);
        System.out.println("GenericJackson2JsonRedisSerializer反序列化时间："+(System.currentTimeMillis()-g_d_start));

        Long j2_s_start = System.currentTimeMillis();
        byte[] bytesJ2 = j2.serialize(list);
        System.out.println("Jackson2JsonRedisSerializer序列化时间："+(System.currentTimeMillis()-j2_s_start) + "ms,序列化后的长度：" + bytesJ2.length);
        Long j2_d_start = System.currentTimeMillis();
        j2.deserialize(bytesJ2);
        System.out.println("Jackson2JsonRedisSerializer反序列化时间："+(System.currentTimeMillis()-j2_d_start));
    }
}
