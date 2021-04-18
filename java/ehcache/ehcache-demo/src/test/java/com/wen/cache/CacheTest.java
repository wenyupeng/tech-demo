package com.wen.cache;

import com.wen.cache.entity.Dog;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: wen
 * @Date: 2021/4/11 19:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class CacheTest {
    @Test
    public void cacheTest() {
        //1.创建缓存管理器
        CacheManager cacheManager = CacheManager.create("src/main/resources/ehcache.xml");
        //2.获取缓存对象
        Cache cache = cacheManager.getCache("HelloWorldCache");
        //3.创建元素
        Element element = new Element("key1", "val1");
        //4.将元素添加到缓存
        cache.put(element);
        //5.获取缓存
        Element e = cache.get("key1");
        System.out.println("element: " + e.toString());
        System.out.println("element1 val: " + e.getObjectValue());
        //6.删除元素
        cache.remove("key1");
        Dog dog = new Dog("little dog", "black", 2);
        Element e2 = new Element("dog", dog);
        cache.put(e2);
        Element element2 = cache.get("dog");
        Dog d= (Dog) element2.getObjectValue();
        System.out.println(d.toString());
        System.out.println(cache.getSize());
        //7.刷新缓存
        cache.flush();
        //8.关闭缓存管理器
        cacheManager.shutdown();
    }
}
