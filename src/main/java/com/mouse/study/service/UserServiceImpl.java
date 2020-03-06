package com.mouse.study.service;
/*
 *created by mouse on 2020/3/5
 */

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisTemplate<String,String> strRedisTemplate;

    //  一共允许用户错误的最大次数
    private final int MAX_NUM = 5;
    //  错误设定时间
    private int ERROR_TIME = 1;
    //  锁定时间
    private int LOCKED_TIME = 5;



    @Override
    public Map<String,Object> AccountIsLocked(String username) {
        String lockedKey = User.getLockKey(username);   //  生成锁定key
        Map<String,Object> map = new HashMap<>();
        if (strRedisTemplate.hasKey(lockedKey)){
            //  redis中有该Key，表示被锁定
            map.put("Flag", true);
            map.put("leftTime", strRedisTemplate.getExpire(lockedKey, TimeUnit.MINUTES));
        }else {
            map.put("Flag", false);
            map.put("leftTime", "1");   //  无所谓
        }

        return map;
    }

    @Override
    public boolean CheckRight(String username, String password) {
        if (username.equals("hurui") && password.equals("hurui")) {
            //  正确
            return true;
        }
        return false;
    }

    @Override
    public String checkErrorKey(String username) {
        String errorKey = User.getErrorKey(username);   //  生成错误key
        //  查看Redis中是否有此key
        if (strRedisTemplate.hasKey(errorKey)) {//  有此key，用户非第一次输入错误
            //  取出key对应的value值
            Long num = Long.parseLong( strRedisTemplate.opsForValue().get(errorKey));
            if (num < MAX_NUM - 1) {//  未达到最大错误次数
                //  更新错误次数
                strRedisTemplate.boundValueOps(errorKey).increment(1);
                //  返回提示信息
                return strRedisTemplate.getExpire(errorKey) + "秒内,您还剩下" + (MAX_NUM - num -1) + "次机会";
            }else {//  达到最大错误次数
                //  锁定账户
                String lockKey = User.getLockKey(username);
                strRedisTemplate.opsForValue().set(lockKey, "locked", LOCKED_TIME, TimeUnit.MINUTES);
                //  返回提示信息
                return "您的错误已达到五次，请"+ strRedisTemplate.getExpire(lockKey,TimeUnit.MINUTES) +"分钟后重试";
            }
        }else {//  没有此key，用户第一次输入错误
            //  创建错误key
            strRedisTemplate.opsForValue().set(errorKey, "1", ERROR_TIME, TimeUnit.MINUTES);
            return "60秒内,您还剩下4次机会";
        }
    }
}
