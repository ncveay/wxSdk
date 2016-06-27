package io.lovezx.wx.sdk.oauth;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.lovezx.wx.sdk.cache.WxCacheService;
import io.lovezx.wx.sdk.context.WxConnectContext;
import io.lovezx.wx.sdk.context.account.WxAccountEntry;

/**
 * Class description:
 * @author yuanyi
 * 2015-4-15 下午4:38:03
 */
public class OauthInterceptor{
	
	 private static Logger LOGGER = LoggerFactory.getLogger(OauthInterceptor.class);
	

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Method handler, Class<?> clazz) throws Exception {
		if(request.getRequestURI().contains("/OauthReceive/receive.3g")){
			return true;
		}
		
		Long start = System.currentTimeMillis();
		LOGGER.debug("拦截URI："+request.getRequestURL().toString());
		//1,获取响应方法，反射确定是否需要验证
		boolean hasOpenId = ParameterNameUtils.hasOpenIdAttr(clazz, handler);
		
    	Oauth needVerify = handler.getAnnotation(Oauth.class);
    	
    	if(!shouldVerify(hasOpenId, needVerify)){
    		return true;
    	}
		//2，需要验证时，判断session中是否有userId标识
			LOGGER.debug("oauth开始session验证");
		if(checkSeesion(request)){ 
			LOGGER.debug("session验证通过");
			return true;
		}
		//3,session验证不成功，进行oauth2.0验证 
		LOGGER.debug("session验证失败，开始Oauth2.0验证");
		Long end = System.currentTimeMillis();
		LOGGER.debug("耗时："+(end-start)+"毫秒");
		if(!"XMLHttpReuqest".equals(request.getHeader("X-Request-With"))){
			sendRedirect(request, response);
			return false;
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("{code:-1, msg:\'登录过期，无法获取用户信息\'}");
		response.getWriter().close();
		return false;
	}

	private boolean shouldVerify(boolean hasOpenId, Oauth needVerify) {
		return hasOpenId && ((needVerify==null || needVerify.value())) || !hasOpenId && (needVerify!=null && needVerify.value());
	}
	
	/**
	 * Method description：校验session中是否存在用户标识
	 * Created by yuanyi 2015-4-15 下午5:15:31
	 * @param request
	 * @return
	 */
	private boolean checkSeesion(HttpServletRequest request){
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute(IWxOauthService.OAUTH_KEY);
		if(StringUtils.isEmpty(id) || id.equals(IWxOauthService.OAUTH_FAIL) || id.equals(IWxOauthService.OAUTH_UNKNOW)){
			return false;
		}
		return true;
	}
	
	/**
	 * 重定向，进行oauth验证，接收controller：OauthReceiveController
	 * */
	private void sendRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//String rootURL = configService.getValueByKey(ConfigKey.WIXIN_ROOT_URL);
		String publicKey = request.getParameter("publicKey");
		if(StringUtils.isBlank(publicKey)) {
			throw new IllegalArgumentException("缺少publicKey参数");
		}
		WxAccountEntry account= WxConnectContext.getInstance().getAccountSupport().getAccount(publicKey);
		String appId = account.getAppid();
		//rootURL = rootURL.replaceAll("/RoxQyWeb", "");
		StringBuilder URL = buildUrl(request);
		String key = storeUrl(URL.toString());
		StringBuilder state = new StringBuilder(key).append(",").append(request.getParameter("publicKey"));
		
		LOGGER.debug("访问地址及publickey："+state);
		int position = request.getRequestURL().indexOf(request.getRequestURI().substring(request.getRequestURI().indexOf("/", 2)));
		String rootURL = request.getRequestURL().substring(0, position);
		String redirectURL = rootURL+"/OauthReceive/receive.3g";  
		LOGGER.debug("重定向地址："+redirectURL);
		
		String oauthURL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId 
						+ "&redirect_uri="+URLEncoder.encode(redirectURL, "UTF-8") 
						+ "&response_type=code&scope=snsapi_base&state="+state.toString()+"#wechat_redirect";
		LOGGER.debug("即将重定向到："+oauthURL);
		
		response.setStatus(307);
		response.setHeader("Location", oauthURL);
		response.setHeader("Connection", "close");
	}

	private StringBuilder buildUrl(HttpServletRequest request) {
		StringBuilder URL = new StringBuilder(request.getRequestURL());
		//String URL = rootURL+request.getRequestURI()+",1";
		
		Map<String , String[]> paramMap = request.getParameterMap();
		Set<Entry<String, String[]>> paramSet = paramMap.entrySet();
		Iterator<Entry<String, String[]>> it = paramSet.iterator();
		if(it.hasNext()){
			URL.append("?");
		}
		//添加参数
		for(;it.hasNext();){
			Entry<String, String[]> entry = it.next();
			String name = entry.getKey();
			String[] values = entry.getValue();
			for(int i=0; i<values.length; i++){
				URL.append(name).append("=").append(values[i]).append("&");
			}
		}
		if(paramSet.size()>=1){
			URL.deleteCharAt(URL.length()-1);
		}
		return URL;
	}

	private String storeUrl(String url) {
		WxCacheService cache = WxConnectContext.getInstance().getCache();
		String key = "OAUTH_VERIFY_"+new Random().nextInt();
		cache.put(key, url, TimeUnit.SECONDS, 20);
		return key;
	}
}
 