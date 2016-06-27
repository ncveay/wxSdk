package io.lovezx.wx.sdk.aop.requestInterceptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import io.lovezx.wx.sdk.api.APIRequestSupport;
import io.lovezx.wx.sdk.api.AbstractResponse;
import io.lovezx.wx.sdk.context.APIContants;
import io.lovezx.wx.sdk.context.CommonContants;
import io.lovezx.wx.sdk.context.WxConnectContext;

public class AccessTokenVerifyInterceptor implements WxRequestInterceptor {

    private Logger LOGGER = LoggerFactory.getLogger(AccessTokenVerifyInterceptor.class);
    private static final ThreadLocal<AtomicInteger> KEYHOLDER = new ThreadLocal<AtomicInteger>();
    
    public boolean preHandler(APIContants api, Object[] args, Object handler, Object obj) {
        return true;
    }

    public void postHandler(APIContants api, Object returnValue, Object[] args,
            Object handler, Object obj) {
        if(!AbstractResponse.class.isInstance(returnValue) 
        		&& !JSONObject.class.isInstance(returnValue)){
           return;
        }
        
        //当返回-1时，重试
        if(!retryCondition(returnValue)){
        	KEYHOLDER.remove();
        	return;
        }
        
        AtomicInteger currentTryTime = KEYHOLDER.get();
        if(currentTryTime == null){
        	currentTryTime = new AtomicInteger(1);
        	KEYHOLDER.set(currentTryTime);
        	
        }
        int time = getConfigTryTime();
        if(currentTryTime.intValue() >= time){
        	LOGGER.error("无法从微信服务器获取数据， 返回数据："+returnValue.toString());
        	KEYHOLDER.remove();
        	return;
        }
        //刷新accessToken
        APIRequestSupport support = (APIRequestSupport)obj;
        WxConnectContext.getInstance().flushAccessToken(support.getAppid());
        currentTryTime.incrementAndGet();
    	returnValue = retry(args, (Method)handler, support);
        if(returnValue == null){
            LOGGER.error("调用异常，请查看日志");
            KEYHOLDER.remove();
            return;
        }

    }

	private int getConfigTryTime() {
		String tryTime = WxConnectContext.getInstance().getCommonContant(CommonContants.CONNECT_RETRY_TIME);
        int time = 1;
        if(StringUtils.isNotEmpty(tryTime) && tryTime.matches("\\d+")){
            time = Integer.parseInt(tryTime);
        }
        return time;
	}


	private boolean retryCondition(Object returnValue) {
		if(AbstractResponse.class.isInstance(returnValue)){
			return retryCondition_1((AbstractResponse)returnValue);
		}else if(JSONObject.class.isInstance(returnValue)){
			return retryCondition_2((JSONObject)returnValue);
		}
		return false;
	}

	private boolean retryCondition_1(AbstractResponse res){
        int errcode = res.getErrcode();
        return checkCondition(errcode);
    }
	private boolean retryCondition_2(JSONObject res){
		if(!res.containsKey("errcode")){
			return false;
		}
		int errcode = res.getInteger("errcode");
        return checkCondition(errcode);
    }
	private boolean checkCondition(int errcode) {
		LOGGER.info("当前调用返回code："+errcode);
        if(errcode == -1) return true;
        if(errcode == 40014) return true;
        if(errcode == 40001) return true;
        if(errcode == 40002) return true;
        if(errcode == 42001) return true;
        return false;
	}
	
    
    
    
    private Object retry(Object[] args, Method targetMethod, Object instance){
        targetMethod.setAccessible(true);
        try {
            return targetMethod.invoke(instance, args);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
