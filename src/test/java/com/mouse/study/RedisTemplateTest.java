package com.mouse.study;
/*
 *created by mouse on 2020/3/4
 */

import entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;

@SpringBootTest
class RedisTemplateTest {

    @Autowired
    private RedisTemplate<String,String> strRedisTemplate;
    @Autowired
    private RedisTemplate<String, Serializable> serializableRedisTemplate;

    @Test
    public void testString(){
        strRedisTemplate.opsForValue().set("name", "hurui");
        System.out.println(strRedisTemplate.opsForValue().get("name"));
    }

    @Test
    public void testSerializable(){
        User user = new User();
        user.setId(1L);
        user.setUserName("hurui");
        user.setPassword("123456");
        serializableRedisTemplate.opsForValue().set("user", user);
        User user2 = (User) serializableRedisTemplate.opsForValue().get("user");
        System.out.println(user2);
        System.out.println("user:"+user2.getId()+","+user2.getUserName()+","+user2.getPassword());
    }




}
