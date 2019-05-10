package com.jin.learn.until;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 常见的redis操作封装
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class CacheUtil {

    private final RedisTemplate<String, Object> redisTemplate;


    public void save(final String key, final Object object) {
        this.save(key, object, 604800L);
    }

    public void save(final String key, final Object obj, final long expire) {
        redisTemplate.opsForValue().set(key, obj, expire, TimeUnit.MILLISECONDS);
    }

    public <T> T get(final String key,Class<T> val) {
        return (T) redisTemplate.opsForValue().get(key);
    }
    /*############list相关操作###########*/
    public Long listLeftPush(final String key, final Object val) {
        return redisTemplate.opsForList().leftPush(key, val);
    }

    public Long listRightPush(final String key, final Object val) {
        return redisTemplate.opsForList().rightPush(key, val);
    }

    public <T> T listRightPop(final String key, Class<T> clazz) {
        return (T) redisTemplate.opsForList().rightPop(key);
    }

    public <T> T listLeftPop(final String key, Class<T> clazz) {
        return (T) redisTemplate.opsForList().leftPop(key);
    }

    /*############map相关操作###########*/
    public void dictKeyAdd(final String key, final String hashKey, final Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    public Long dictKeyRemove(final String key, final Object... hashKey) {
        return redisTemplate.opsForHash().delete(key, hashKey);
    }

    public Map getEntireDict(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /*############set相关操作###########*/
    public Set getAllSet(final String key) {
        return redisTemplate.opsForSet().members(key);
    }


    public Boolean exprire(final String key,long time) {
        return redisTemplate.expire(key, time, TimeUnit.MILLISECONDS);
    }


    public Boolean delete(final String key) {
        return redisTemplate.delete(key);
    }

    public Boolean lock(final String key, final String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, 30, TimeUnit.SECONDS);
    }

    public Boolean release(final String key) {
        return delete(key);
    }

}
