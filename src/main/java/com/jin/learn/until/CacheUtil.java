package com.jin.learn.until;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jin.learn.entity.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;


@Component
@Slf4j
@RequiredArgsConstructor
public class CacheUtil {

    private final RedisTemplate<String, Object> redisTemplate;


    /**
     * 保存到缓存，过期时间为默认过期时间
     *
     * @param key     缓存key
     * @param seriObj 缓存数据
     */
    public void save(final String key, final Object object) {
        this.save(key, object, 604800L);
    }

    /**
     * 保存到缓存，并设定过期时间
     *
     * @param key    缓存key
     * @param obj    缓存数据
     * @param expire 过期时间
     */
    public void save(final String key, final Object obj, final long expire) {
        redisTemplate.opsForValue().set(key, obj, expire, TimeUnit.MILLISECONDS);
    }


    /**
     * 获取缓存对象的字符串值
     *
     * @param key 缓存key
     * @return
     */
    public String get(final String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取缓存对象
     *
     * @param key 缓存key
     * @return
     */
    public <T> T get(final String key, Class<T> clazz) {
         return JSON.parseObject((String) redisTemplate.opsForValue().get(key), clazz);
    }

    public Set<String> getSet(final String key) {
        return (Set<String>)redisTemplate.opsForValue().get(key);
    }

    /**
     * 锁住某个key值，需要解锁时删除即可
     *
     * @param key
     * @param value
     * @return
     */
    public boolean lock(final String key, final String value) {
        return false;
    }


    /**
     * 删除缓存
     *
     * @param key 需要删除缓存的id
     */
    public Boolean delete(final String key) {
        return redisTemplate.delete(key);
    }


}
