package io.lovezx.wx.sdk.aop.requestInterceptor;

import io.lovezx.wx.sdk.context.APIContants;

/**
 * 微信api调用拦截器接口
 * @author yuanyi
 *
 */
public interface WxRequestInterceptor {
    /**
     * 前置处理
     * @param api 被调用的api接口
     * @param args 调用的参数
     * @param handler 处理器（暂时为被拦截的方法）
     * @param obj 被拦截的对象
     * @return
     */
    public boolean preHandler(APIContants api, Object[] args, Object handler, Object obj);
    /**
     * 后置处理
     * @param api 被调用的api接口
     * @param returnValue 调用后的返回值
     * @param args 调用的参数
     * @param handler 处理器（暂时为被拦截的方法）
     * @param obj 被拦截的对象
     */
    public void postHandler(APIContants api, Object returnValue, Object[] args, Object handler, Object obj);
    
}
