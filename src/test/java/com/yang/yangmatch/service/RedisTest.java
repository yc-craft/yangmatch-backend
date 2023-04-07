package com.yang.yangmatch.service;

import com.yang.yangmatch.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

@SpringBootTest
public class RedisTest {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void test() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        // 增
        valueOperations.set("yangString", "dog");
        valueOperations.set("yangInt", 1);
        valueOperations.set("yangDouble", 2.0);
        User user = new User();
        user.setId(1L);
        user.setUsername("yang");
        valueOperations.set("yangUser", user);
        // 查
        Object yang = valueOperations.get("yangString");
        Assertions.assertTrue("dog".equals((String) yang));
        yang = valueOperations.get("yangInt");
        Assertions.assertTrue(1 == (Integer) yang);
        yang = valueOperations.get("yangDouble");
        Assertions.assertTrue(2.0 == (Double) yang);
        System.out.println(valueOperations.get("yangUser"));
        valueOperations.set("yangString", "dog");
        redisTemplate.delete("yangString");
    }
}
