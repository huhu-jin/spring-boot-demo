package com.jin.learn.until.cache;

import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.NullValue;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 二级缓存
 */
@Component
@RequiredArgsConstructor
public class LayeringCache {

    private static Logger logger = LoggerFactory.getLogger(LayeringCache.class);

    private final RedisConnectionFactory redisConnectionFactory;


    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 缓存的名称
     */
    private  String name;

    /**
     * 是否使用一级缓存
     */
    private boolean usedFirstCache = true;


    private RedisCache redisCache;

    /**
     * Caffeine缓存
     */
    private CaffeineCache caffeineCache;

    @PostConstruct
    public void after(){
        RedisCacheManager redisCacheManager = RedisCacheManager.create(redisConnectionFactory);
        this.redisCache = (RedisCache)redisCacheManager.getCache("abc");

    }

    public LayeringCache getNativeCache() {
        return this;
    }


    public RedisCache getSecondaryCache() {
        return this.redisCache;
    }

    public CaffeineCache getFirstCache() {
        return this.caffeineCache;
    }

    public RedisTemplate<String, Object> getScondaryChacheOpration(){
        return redisTemplate;
    }

    public ValueWrapper get(Object key) {
        ValueWrapper wrapper = null;
        if (usedFirstCache) {
            // 查询一级缓存
            wrapper = caffeineCache.get(key);
        }

        if (wrapper == null) {
            // 查询二级缓存
            wrapper = redisCache.get(key);
        }
        return wrapper;
    }

    public <T> T get(Object key, Class<T> type) {
        T value = null;
        if (usedFirstCache) {
            // 查询一级缓存
            value = caffeineCache.get(key, type);
            logger.debug("查询一级缓存 key:{},返回值是:{}", key, JSON.toJSONString(value));
        }

        if (value == null) {
            // 查询二级缓存
            value = redisCache.get(key, type);
            caffeineCache.put(key, value);
            logger.debug("查询二级缓存 key:{},返回值是:{}", key, JSON.toJSONString(value));
        }
        return value;
    }

    public <T> T get(Object key, Callable<T> valueLoader) {
        T value = null;
        if (usedFirstCache) {
            // 查询一级缓存,如果一级缓存没有值则调用getForSecondaryCache(k, valueLoader)查询二级缓存
            value = (T) caffeineCache.getNativeCache().get(key, k -> getForSecondaryCache(k, valueLoader));
        } else {
            // 直接查询二级缓存
            value = (T) getForSecondaryCache(key, valueLoader);
        }

        if (value instanceof NullValue) {
            return null;
        }
        return value;
    }

    public void put(Object key, Object value) {
        if (usedFirstCache) {
            caffeineCache.put(key, value);
        }
        redisCache.put(key, value);
    }

    public ValueWrapper putIfAbsent(Object key, Object value) {
        if (usedFirstCache) {
            caffeineCache.putIfAbsent(key, value);
        }
        return redisCache.putIfAbsent(key, value);
    }

    public void evict(Object key) {
        // 删除的时候要先删除二级缓存再删除一级缓存，否则有并发问题
        redisCache.evict(key);
        if (usedFirstCache) {
            // 删除一级缓存需要用到redis的订阅/发布模式，否则集群中其他服服务器节点的一级缓存数据无法删除
            Map<String, Object> message = new HashMap<>();
            message.put("cacheName", name);
            message.put("key", key);
            // 创建redis发布者
            RedisPublisher redisPublisher = new RedisPublisher(redisTemplate, ChannelTopicEnum.REDIS_CACHE_DELETE_TOPIC.getChannelTopic());
            // 发布消息
            redisPublisher.publisher(message);
        }
    }

    public void clear() {
        redisCache.clear();
        if (usedFirstCache) {
            // 清除一级缓存需要用到redis的订阅/发布模式，否则集群中其他服服务器节点的一级缓存数据无法删除
            Map<String, Object> message = new HashMap<>();
            message.put("cacheName", name);
            // 创建redis发布者
            RedisPublisher redisPublisher = new RedisPublisher(redisTemplate, ChannelTopicEnum.REDIS_CACHE_CLEAR_TOPIC.getChannelTopic());
            // 发布消息
            redisPublisher.publisher(message);
        }
    }

    protected Object lookup(Object key) {
        Object value = null;
        if (usedFirstCache) {
            value = caffeineCache.get(key);
            logger.debug("查询一级缓存 key:{},返回值是:{}", key, JSON.toJSONString(value));
        }
        if (value == null) {
            value = redisCache.get(key);
            logger.debug("查询二级缓存 key:{},返回值是:{}", key, JSON.toJSONString(value));
        }
        return value;
    }

    /**
     * 查询二级缓存
     *
     * @param key
     * @param valueLoader
     * @return
     */
    private <T> Object getForSecondaryCache(Object key, Callable<T> valueLoader) {
        T value = redisCache.get(key, valueLoader);
        return value;
    }
}