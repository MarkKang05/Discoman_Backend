package com.mywork.discoman.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate redisTemplate;

//    @Value("jwt.refresh_expiration_time")
//    private long REFRESH_EXPIRATION_TIME;

    public void setValues(String token, String username){
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(token, username, Duration.ofMinutes(2));
    }

    public String getValues(String token){
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        return values.get(token);
    }

    public void delValues(String token) {
        redisTemplate.delete(token);
    }


}
