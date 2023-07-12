package com.example.demo.service;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;

import java.util.Collections;
import java.util.List;

public class UpdateData implements SessionCallback<List<Object>> {

    private final String newValue;

    public UpdateData(String newValue) {
        this.newValue = newValue;
    }

    @Override
    public <K, V> List<Object> execute(RedisOperations<K, V> operations) throws DataAccessException {
        RedisOperations<String, String> redisOperations = (RedisOperations<String, String>) operations;
        ListOperations<String, String> listOperations = redisOperations.opsForList();
        try {
            redisOperations.multi();
            listOperations.leftPush("message", this.newValue);
            return redisOperations.exec();
        } catch (Exception e) {
            redisOperations.discard();
        }
        return Collections.emptyList();
    }
}
