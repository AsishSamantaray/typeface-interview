package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void saveData(List<String> messages) {
        redisTemplate.opsForList().leftPushAll("message", messages);
    }

    public void addNewData(String message) {
        redisTemplate.execute(new UpdateData(message));
    }

    public List<String> getValue(String key) {
        Long size = redisTemplate.opsForList().size(key);
        return redisTemplate.opsForList().range(key, 0, -1);
    }
}
