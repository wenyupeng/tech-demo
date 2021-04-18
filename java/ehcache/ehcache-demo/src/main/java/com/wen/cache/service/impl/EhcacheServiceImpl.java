package com.wen.cache.service.impl;

import com.wen.cache.service.EhcacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Author: wen
 * @Date: 2021/4/11 20:46
 */
@Service
@Slf4j
public class EhcacheServiceImpl implements EhcacheService {

    @Override
    @Cacheable(value = "cache-01", key = "#key")
    public String getTimestamp(String key) {
        Long timestamp = System.currentTimeMillis();
        return timestamp.toString();
    }

    @Override
    @Cacheable(value = "cache-01", key = "'timestamp:' + #key")
    public String cacheWithPrefix(String key) {
        Long timestamp = System.currentTimeMillis();
        return "timestamp:"+timestamp;
    }

    @Override
    @CachePut(value = "cache-01", key = "#key")
    public String updateTimeStamp(String key) {
        Long timestamp = System.currentTimeMillis();
        log.info("update timestamp {}",timestamp);
        return timestamp.toString();
    }



    @Override
    @Cacheable(value = "cache-01", condition = "#key.length()<12")
    public boolean cacheWithCondition(String key) {
        System.out.println("UserCache:" + key);
        return false;
    }

    @Override
    @CacheEvict(value = "cache-01", key = "#key")
    public void removeCacheByKey(String key) {
        System.out.println("cache-01 remove:" + key);
    }

    @Override
    @CacheEvict(value = "cache-01", allEntries = true)
    public void removeAllCache() {
        System.out.println("cache-01 delete all");
    }
}
