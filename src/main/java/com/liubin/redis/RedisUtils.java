package com.liubin.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author liubin
 * @description redis工具类
 * @date 2020/2/5 12:21
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    public String getType(String key) {
        return redisTemplate.type(key).code();
    }

    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }

    public void rename(String key, String newName) {
        redisTemplate.rename(key, newName);
    }

    public Boolean exists(String key) {
        return redisTemplate.countExistingKeys(Arrays.asList(key)) > 0;
    }

    public void flushAll() {
        //TODO
    }

    public long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    public void setString(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Long setList(String key, Object... value) {
        return redisTemplate.opsForList().rightPushAll(key, value);
    }

    public Long setSet(String key, Object... value) {
        return redisTemplate.opsForSet().add(key, value);
    }

    public void setHash(String key, Map map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    public boolean setZSet(String key, Object value, double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    public Object getString(String key) {
        return redisTemplate.boundValueOps(key).get();

    }

    public Map getHash(String key) {
        return redisTemplate.boundHashOps(key).entries();

    }

    public List getList(String key) {
        return redisTemplate.boundListOps(key).range(0, -1);
    }

    public Set getSet(String key) {
        return redisTemplate.boundSetOps(key).members();

    }

    public Set getZSet(String key) {
        return redisTemplate.boundZSetOps(key).range(0, -1);

    }

}