package io.lovezx.wx.sdk.context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.lovezx.wx.sdk.aop.requestInterceptor.WxRequestInterceptor;
import io.lovezx.wx.sdk.api.base.WxAccessTokenAPI;
import io.lovezx.wx.sdk.cache.CacheContants;
import io.lovezx.wx.sdk.cache.WxCacheService;
import io.lovezx.wx.sdk.context.account.WxAccountEntry;
import io.lovezx.wx.sdk.context.account.WxAccountSupport;
import io.lovezx.wx.sdk.http.HttpsRequest;
import io.lovezx.wx.sdk.resource.ResourceLoader;

/**
 * 链接微信API配置上下文
 * @author yuanyi
 *
 */
public class WxConnectContext {
    
    private static final int DEFAULT_ACCESS_TOKEN_EXPIRE = 7000;
    private static final int DEFAULT_INTERVAL_TIME = 300;
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");
    private static final Logger LOGGER = LoggerFactory.getLogger(WxConnectContext.class);
    private static final String DEFAULT_COMMONCONFIG_PATH = "classPath:wxConfig.properties";
    private static final String DEFAULT_APICONFIG_PATH = "wxApi.properties";
    /*缓存*/
    private WxCacheService cache;
    /*公众号账户获取接口*/
    private WxAccountSupport accountSupport;
    /*api地址常量*/
    private EnumMap<APIContants, String> apiContants = new EnumMap<APIContants, String>(APIContants.class);;
    /*缓存时间等常量*/
    private EnumMap<CommonContants, String> commonContants = new EnumMap<CommonContants, String>(CommonContants.class);
    /*一般http链接服务*/
    private final HttpsRequest commonRequest = HttpsRequest.getCommonHttpServer();
    /*支付http链接服务*/
    private Map<String, HttpsRequest> payRequest = new HashMap<String, HttpsRequest>();
    /*微信api访问拦截器链---访问接口*/
    private final List<WxRequestInterceptor> reqInterceptors;
    
    private static WxConnectContext context;
   
    public WxConnectContext (List<WxRequestInterceptor> list){
        reqInterceptors = Collections.unmodifiableList(list);
    }
    
    public WxCacheService getCache() {
        return cache;   
    }
    public void setCache(WxCacheService cache) {
        this.cache = cache;
    }
    public WxAccountSupport getAccountSupport() {
        return accountSupport;
    }
    public void setAccountSupport(WxAccountSupport accountSupport) {
        this.accountSupport = accountSupport;
    }
    
    public static WxConnectContext getInstance(){
        return context;
    }
    public String getCommonContant(CommonContants contant){
        return commonContants.get(contant);
    }
    
    public String getApiContant(APIContants contant){
        return apiContants.get(contant);
    }
    
    public HttpsRequest getCommonHttpServer() {
        return commonRequest.copy();
    }
    
    public HttpsRequest getPayHttpServer(String appid) {
        return payRequest.get(CacheContants.ACCOUNT_.name()+appid).copy();
    }
    
    public List<WxRequestInterceptor> getReqInterceptors() {
        return reqInterceptors;
    }

    /**
     * 初始化上下文
     */
    private String commonConfig = DEFAULT_COMMONCONFIG_PATH;
    private String apiConfig = DEFAULT_APICONFIG_PATH;
    
    public String getCommonConfig() {
        return commonConfig;
    }
    public String getApiConfig() {
        return apiConfig;
    }
    public void setCommonConfig(String commonConfig) {
        this.commonConfig = commonConfig;
    }
    public void setApiConfig(String apiConfig) {
        this.apiConfig = apiConfig;
    }
    
    public void init(){
        LOGGER.info("开始初始化WxConnectContext……");
        if(cache == null) throw new IllegalArgumentException("无法加载缓存服务！");
        ResourceLoader loader = new ResourceLoader();
        if(StringUtils.isEmpty(commonConfig)){
            commonConfig = DEFAULT_COMMONCONFIG_PATH;
        }
        if(StringUtils.isEmpty(apiConfig)){
            apiConfig = DEFAULT_APICONFIG_PATH;
        }
        List<WxAccountEntry> accountList = accountSupport.getAllAccount();
        for(WxAccountEntry entry : accountList){
            cache.put(CacheContants.ACCOUNT_.name()+entry.getAppid(), entry);
        }
        try {
            Properties commonProp = loader.loadProperties(commonConfig);
            /*加载一般配置数据*/ 
            loadCommonProp(commonProp);
            //Properties apiPop = loader.loadProperties(ClassLoader.getSystemResourceAsStream(apiConfig));
            Properties apiPop = loader.loadProperties(this.getClass().getClassLoader().getResourceAsStream(apiConfig));
            //Properties apiPop = loader.loadProperties(apiConfig);
            /*加载API配置*/
            loadApiProp(apiPop);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LOGGER.error("找不到指定配置文件!", e);
            throw new ConfigurationException(e);
        }
        context = this;
        
        initPayServer(accountList);
        
        LOGGER.info("初始化WxConnectContext完成");
    }
    
    private void initPayServer(List<WxAccountEntry> accounts) {
        for(WxAccountEntry entry : accounts){
        	if(StringUtils.isBlank(entry.getCertPath())) {
        		LOGGER.error("支付配置失败, 无效的证书地址 ,证书地址为空。 token==>"+entry.getToken());
        		continue;
        	}
        	try{
	            KeyStore keyStore = KeyStore.getInstance("PKCS12");
	            FileInputStream instream = new FileInputStream(entry.getCertPath());//加载本地的证书进行https加密传输
	            try {
		            if(!instream.getFD().valid()){
		            	LOGGER.error("支付配置失败, 无效的证书地址 ,证书地址为["+entry.getCertPath()+"] 。 token==>"+entry.getToken());
		            	continue;
		            }
	                keyStore.load(instream, entry.getMchid().toCharArray());//设置证书密码
	            } catch (CertificateException e) {
	                LOGGER.error("init pay config fail, appid "+entry.getAppid(), e);
	                continue;
	            } catch (NoSuchAlgorithmException e) {
	                LOGGER.error("init pay config fail, appid "+entry.getAppid(), e);
	                continue;
	            } finally {
	                instream.close();
	            }
	            LOGGER.info("支付配置成功, 证书地址为["+entry.getCertPath()+"] 。 appid==>"+entry.getAppid()+", key==>"+entry.getKey());
	            payRequest.put(CacheContants.ACCOUNT_.name()+entry.getAppid(), HttpsRequest.getHttpsServer(keyStore,entry.getMchid()));
        	}catch(Exception e){
        		LOGGER.error("支付配置失败, token==>"+entry.getToken(), e);
        	}
        }
    }
    
    private void loadCommonProp(Properties prop){
        Set<String> set = prop.stringPropertyNames();
        for(String name : set){
        	CommonContants contants = CommonContants.valueOf(name.toUpperCase());
            if(contants == null) {
                LOGGER.warn("无效的配置项："+name);
                continue;
            }
            String value = prop.getProperty(name);
            if(StringUtils.isEmpty(value)){
                throw new ConfigurationException("配置项不能为空："+name);
            }
            commonContants.put(CommonContants.valueOf(name), prop.getProperty(name));
        }
    }
    
    private void loadApiProp(Properties prop){
        Set<String> set = prop.stringPropertyNames();
        for(String name : set){
            APIContants contants = APIContants.valueOf(name.toUpperCase());
            if(contants == null) {
                LOGGER.warn("无效的配置项："+name);
                continue;
            }
            String value = prop.getProperty(name);
            if(StringUtils.isEmpty(value)){
                throw new ConfigurationException("配置项不能为空："+name);
            }
            apiContants.put(APIContants.valueOf(name), prop.getProperty(name));
        }
    }
    
    /**
     * 获取ACCESS_TOKEN
     * @return
     */
    public String getAccessToken(String appid){
        String cacheKey = CacheContants.ACCESS_TOKEN.name()+appid;
        String accessToken = (String)cache.get(cacheKey);
        if(StringUtils.isNotBlank(accessToken)){
            return accessToken;
        }
        /*newToken由WxBaseAPI保证不为空或者其他非法token，这里不做检查*/
        WxAccessTokenAPI api = new WxAccessTokenAPI();
        api.SetAppid(appid);
        
        String newToken = api.getAccessToken();
        int expire = getExpireTime();
        cache.put(cacheKey, newToken, TimeUnit.SECONDS, expire);
        return newToken;
    }
   
    /**
     * 重新获取新的AccessToken
     */
    public String flushAccessToken(String appid){
    	if(getIntervalTime()+getLastFlushTime() > System.currentTimeMillis()){
    		return getAccessToken(appid);
    	}
        cache.remove(CacheContants.ACCESS_TOKEN.name()+appid);
        String token = getAccessToken(appid);
        cache.put(CacheContants.LAST_FLUSH_TIME.name(), System.currentTimeMillis());
        return token;
    }
	
    private long getLastFlushTime() {
		Object last_flush_time_str = cache.get(CacheContants.LAST_FLUSH_TIME.name());
		if(last_flush_time_str == null){
			return 0L;
		}
    	return (Long)last_flush_time_str;
	}
    
	private static volatile int expireTime = 0;
	private int getExpireTime() {
		if(expireTime > 0){
			return expireTime;
		}
		String expireTime = commonContants.get(CommonContants.ACCESS_TOKEN_EXPIRE_TIME);
        int expire = DEFAULT_ACCESS_TOKEN_EXPIRE;
        if(!NUMBER_PATTERN.matcher(expireTime).matches()){
        	LOGGER.warn("accessToken缓存时间未设置或错误，使用默认时间：7000秒");
        }else{
        	expire = Integer.parseInt(expireTime);
        }
		return expire;
	}
	
    private static volatile int intervalTime = 0;
	private int getIntervalTime() {
		if(intervalTime > 0){
			return intervalTime;
		}
		String time = commonContants.get(CommonContants.FLUSH_ACCESS_TOKEN_INTERVAL_TIME);
		int interval_time = DEFAULT_INTERVAL_TIME;
    	if(!NUMBER_PATTERN.matcher(time).matches()){
    		LOGGER.warn("accessToken刷新间隔时间未设置或错误，使用默认时间：300秒");
    	}else{
    		interval_time = Integer.parseInt(time);
    	}
    	return interval_time;
	}
}
