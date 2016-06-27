package io.lovezx.wx.sdk.cache;

import java.util.concurrent.TimeUnit;

/**
 * 为API提供缓存服务， 如缓存ACCESS_TOKEN.
 * 实现者需保证线程安全
 * @author yuanyi
 *
 */
public interface WxCacheService {
    
    /**
     * 保存一个键值对
     * @param key
     * @param value
     * @return 如果此key已经存在value，则返回此value
     * @throws NullPointException 当key或者value为空时抛出
     */
    Object put(String key, Object value);
    
    /**
     * 保存一个键值对,在unit为单位的time时长后被清除
     * @param key
     * @param value
     * @param unit 时间单位
     * @param time 时间值
     * @return 如果此key已经存在value，则返回此value
     * @throws NullPointException 当key，value或者<code>unit<code>为空时抛出
     * @throws IllegalArugumentsException 当time为0或者负数时抛出
     */
    Object put(String key, Object value, TimeUnit unit, long time);
    
    /**
     * 移除缓存中的值
     * @param key
     * @return 返回此key对应的value
     * @throws NullPointException 当key为空时抛出
     */
    Object remove(String key);
    
    /**
     * 查询缓存中是否保存此key
     * @param key
     * @return 保存此key时返回<code>true<code>
     * @throws NullPointException 当key为空时抛出
     */
    boolean existKey(String key);
    
    /**
     * 获取指定key的值
     * @param key
     * @return
     * @throws NullPointException 当key为空时抛出
     */
    Object get(String key);
    
}
