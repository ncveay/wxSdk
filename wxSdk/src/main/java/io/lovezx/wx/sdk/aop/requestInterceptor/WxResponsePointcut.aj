package com.cfwx.rox.weixin.sdk.aop.requestInterceptor;

import java.lang.reflect.Method;
import java.util.List;

import com.cfwx.rox.weixin.sdk.api.AccessTokenRetrySupport;
import com.cfwx.rox.weixin.sdk.context.APIContants;
import com.cfwx.rox.weixin.sdk.context.WxConnectContext;
/**
 * 微信接口返回值切面规则
 * @author yuanyi
 *
 */
public aspect WxResponsePointcut {
	
    pointcut tracedCall(): target(AccessTokenRetrySupport) && 
        execution(@PointCut * *(APIContants, ..));
    
    
    before(): tracedCall(){
        List<WxRequestInterceptor> interceptors = WxConnectContext.getInstance().getReqInterceptors();
        Method method = findMethod(thisJoinPoint.getArgs(), thisJoinPoint.getTarget(), thisJoinPoint.getSignature().getName());
        for(WxRequestInterceptor interceptor : interceptors){
            if(!interceptor.preHandler((APIContants)thisJoinPoint.getArgs()[0], thisJoinPoint.getArgs(), method, thisJoinPoint.getThis())){
                break;
            }
        }
    }
    
    after() returning(Object res) : tracedCall(){
        List<WxRequestInterceptor> interceptors = WxConnectContext.getInstance().getReqInterceptors();
        Method method = findMethod(thisJoinPoint.getArgs(), thisJoinPoint.getTarget(), thisJoinPoint.getSignature().getName());
        for(WxRequestInterceptor interceptor : interceptors){
            interceptor.postHandler((APIContants)thisJoinPoint.getArgs()[0], res, thisJoinPoint.getArgs(), method, thisJoinPoint.getThis());
        }
    }
    
    private Method findMethod(Object[] args, Object instance, String methodName){
        Class<?> clazz = instance.getClass().getSuperclass();
        try {
            Method[] methods = clazz.getDeclaredMethods();
            for(Method method : methods){
            	//使用方法名进行匹配，如果有方法重载则会有问题。所以对应类中被横切的方法不应当有重载的方法
                if(methodName.equals(method.getName())){
                    return method;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
