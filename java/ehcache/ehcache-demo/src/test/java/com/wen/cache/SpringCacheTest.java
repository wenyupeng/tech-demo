package com.wen.cache;

import com.wen.cache.service.EhcacheService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: wen
 * @Date: 2021/4/11 20:48
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringCacheTest {
    @Autowired
    private EhcacheService ehcacheService;
    @Autowired
    CacheManager cacheManager;

    @Test
    public void testGetTimestamp() throws InterruptedException{
        System.out.println("第一次调用：" + ehcacheService.getTimestamp("randomKey"));
        Thread.sleep(2000);
        System.out.println("2秒之后调用：" + ehcacheService.getTimestamp("randomKey"));
        Thread.sleep(2000);
        System.out.println("再过4秒之后调用：" + ehcacheService.getTimestamp("randomKey"));
    }

    @Test
    public void testCacheWithPrefix() throws InterruptedException {
        String val = ehcacheService.cacheWithPrefix("prefix-test");
        System.out.println(val);
        Thread.sleep(2000);
        System.out.println(ehcacheService.cacheWithPrefix("prefix-test"));
        Cache cache = cacheManager.getCache("cache-01");
        cache.getKeys().forEach(System.out::println);
    }

    @Test
    public void testUpdateTimeStamp(){
        String timestamp = ehcacheService.getTimestamp("key");
        System.out.println(timestamp);
        String timeStamp = ehcacheService.updateTimeStamp("key");
        System.out.println(timeStamp);
    }

    @Test
    public void testCacheWithCondition() {
        System.out.println(ehcacheService.cacheWithCondition("key"));
        Cache cache = cacheManager.getCache("cache-01");
        cache.getKeys().forEach(System.out::println);
        System.out.println(ehcacheService.cacheWithCondition("123456789101212"));
        cache = cacheManager.getCache("cache-01");
        cache.getKeys().forEach(System.out::println);
    }

    @Test
    public void testRemoveCacheByKey() {
        System.out.println(ehcacheService.getTimestamp("key"));
        Cache cache = cacheManager.getCache("cache-01");
        cache.getKeys().forEach(System.out::println);
        ehcacheService.removeCacheByKey("key");
        cache = cacheManager.getCache("cache-01");
        cache.getKeys().forEach(System.out::println);
    }

    @Test
    public void testRemoveAllCache() {
        System.out.println(ehcacheService.getTimestamp("key"));
        Cache cache = cacheManager.getCache("cache-01");
        cache.getKeys().forEach(System.out::println);
        ehcacheService.removeAllCache();
        cache = cacheManager.getCache("cache-01");
        cache.getKeys().forEach(System.out::println);
    }
}
