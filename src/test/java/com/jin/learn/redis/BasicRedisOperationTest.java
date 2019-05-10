package com.jin.learn.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jin.learn.Application;
import com.jin.learn.entity.Role;
import com.jin.learn.until.CacheUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BasicRedisOperationTest {


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Autowired
    private CacheUtil cacheUtil;

    @Test
    public void testPutString() {
        redisTemplate.opsForValue().set("ssss", "123", 1000, TimeUnit.MICROSECONDS);
        redisTemplate.opsForValue().set("abc", "123");
    }

    @Test
    public void testGetString() {
        Object ssss = redisTemplate.opsForValue().get("ssss");
        System.out.println(ssss);
        Object abc = redisTemplate.opsForValue().get("abc");
        System.out.println(abc);
    }


    @Test
    public void testPutObject() {
        Role role = new Role();
        role.setId(123L);
        role.setCreateTime(new Date());
        redisTemplate.opsForValue().set("obj", role);
    }


    @Test
    public void testGetObject() {
        String obj = (String) redisTemplate.opsForValue().get("obj");
        Role parse = JSON.parseObject(obj, Role.class);
        System.out.println(parse.getCreateTime());
    }

    @Test
    public void testInc() {
        redisTemplate.opsForValue().increment("aa"); // 默认0
    }

    @Test
    public void testCacheUtil() {
        Role obj = cacheUtil.get("obj12", Role.class);
    }



}
