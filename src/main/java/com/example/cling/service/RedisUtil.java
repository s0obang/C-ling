package com.example.cling.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisUtil {
    private final StringRedisTemplate redisTemplate;

    public String getData(String key) {
        if (key == null) {
            throw new IllegalArgumentException("키는 null일 수 없습니다.");
        }
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public void setData(String key, String value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("키와 값은 null일 수 없습니다.");
        }
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    public void setDataExpire(String key, String value, long duration) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("키와 값은 null일 수 없습니다.");
        }
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        Duration expireDuration = Duration.ofSeconds(duration);
        valueOperations.set(key, value, expireDuration);
    }

    public void deleteData(String key) {
        if (key == null) {
            throw new IllegalArgumentException("키는 null일 수 없습니다.");
        }
        redisTemplate.delete(key);
    }
}