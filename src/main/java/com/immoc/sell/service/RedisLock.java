package com.immoc.sell.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean lock(String key, String value) {
        // key: productId, value ：当前时间+超时时间
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }
        // 防止加锁、解锁过程中代码出现异常。导致锁一直处理关闭状态；
        String currentValue = redisTemplate.opsForValue().get(key);
        // 如果锁过期
        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            // 获取上一个锁的时间
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);  // 将key对应的值设置为新的value并且获取到上一个value
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                // 当有多个线程进入到这里的时候，!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)保证了只有一个线程拿到的oldValue跟currentValue是相等的
                return true;
            }
        }
        return false;
    }

    public void unlock(String key, String value) {
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("【redis分布式锁】解锁异常,{}", e.getMessage());
        }
    }
}
