package com.wen.cache.service;

/**
 * @Author: wen
 * @Date: 2021/4/11 20:44
 */
public interface EhcacheService {
    /**
     * 获取时间戳
     */
    public String getTimestamp(String key);

    /**
     * key带前缀的方式缓存时间戳
     */
    public String cacheWithPrefix(String key);
    /**
     * 更新时间戳
     */
    public String updateTimeStamp(String key) ;

    /**
     * 根据条件缓存值
     */
    public boolean cacheWithCondition(String key);

    /**
     * 根据key移除缓存值
     */
    public void removeCacheByKey(String key) ;

    /**
     * 清空缓存
     */
    public void removeAllCache();
}
