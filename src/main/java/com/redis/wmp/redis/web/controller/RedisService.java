package com.redis.wmp.redis.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RedisService {

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisService(StringRedisTemplate stringRedisTemplate)
    {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Transactional
    public void insert(String str)
    {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();

        for (int i = 0; i < 10; i++)
        {
            valueOperations.set(str + i, String.valueOf(i));
        }
    }

    public String getStr(String str)
    {
        return stringRedisTemplate.opsForValue().get(str);
    }
}
