package com.mouse.study.Conf;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/*
 *  created by mouse on 2020/3/4
 *  Redis中Lettuce的配置文件
 */
@Configuration
@EnableCaching
public class LettuceRedisConfig extends CachingConfigurerSupport {


    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory connectionFactory) {
        //  改变默认的RedisTemplate
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        //  设置RedisTemplate中的key存储形式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //  设置RedisTemplate中value的存储形式
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }
}
