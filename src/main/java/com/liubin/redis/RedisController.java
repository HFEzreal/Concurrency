package com.liubin.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author liubin
 * @description redis测试方法
 * @date 2020/2/5 13:08
 */
@RestController
@RequestMapping("redis")
public class RedisController {
    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("getType")
    public String getType(String key) {
        return redisUtils.getType(key);
    }

    @RequestMapping("del")
    public Boolean del(String key) {
        return redisUtils.del(key);
    }

    @RequestMapping("rename")
    public void rename(String key, String newName) {
        redisUtils.rename(key, newName);
    }

    @RequestMapping("exists")
    public Boolean exists(String key) {
        return redisUtils.exists(key);
    }

    @RequestMapping("getExpire")
    public long getExpire(String key) {
        return redisUtils.getExpire(key);
    }

    @RequestMapping("setString")
    public void setString(String key, String value) {
        redisUtils.setString(key, value);
    }

    @RequestMapping("setList")
    public Long setList(String key, String values) {
        return redisUtils.setList(key, values.split(","));
    }

    @RequestMapping("setSet")
    public Long setSet(String key, String values) {
        return redisUtils.setSet(key, values.split(","));
    }

    @RequestMapping("setHash")
    public void setHash(String key, @RequestBody Map map) {
        redisUtils.setHash(key, map);
    }

    @RequestMapping("setZSet")
    public boolean setZSet(String key, String value, double score) {
        return redisUtils.setZSet(key, value, score);
    }

    @RequestMapping("getString")
    public Object getString(String key) {
        return redisUtils.getString(key);

    }

    @RequestMapping("getHash")
    public Map getHash(String key) {
        return redisUtils.getHash(key);

    }

    @RequestMapping("getList")
    public String getList(String key) {
        return redisUtils.getList(key).toString();

    }

    @RequestMapping("getSet")
    public String getSet(String key) {
        return redisUtils.getSet(key).toString();

    }

    @RequestMapping("getZSet")
    public String getZSet(String key) {
        return redisUtils.getZSet(key).toString();

    }
}