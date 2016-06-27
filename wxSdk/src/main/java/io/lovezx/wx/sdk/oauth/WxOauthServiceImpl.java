package io.lovezx.wx.sdk.oauth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import io.lovezx.wx.sdk.context.WxConnectContext;
import io.lovezx.wx.sdk.context.account.WxAccountEntry;
import io.lovezx.wx.sdk.http.HttpsRequest;

/**
 * Class description:当传递的state参数需要更改时，只需要更改verify方法的逻辑即可
 * @author yuanyi
 * 2015-5-25 下午5:50:35
 */
public class WxOauthServiceImpl extends IWxOauthService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(WxOauthServiceImpl.class);
	
	@Override
	public String doVerify(HttpServletResponse response, HttpServletRequest request, String code, String state) {
		Long start = System.currentTimeMillis();
		// 根据code获取成员信息 API
		// https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE&agentid=AGENTID
		LOGGER.debug("访问地址："+request.getRequestURL());
		LOGGER.debug("code:"+code);
		LOGGER.debug("state:"+state);
		//redisTemplate.opsForValue().set(RedisKey.OAUTH_VERIFY_.getKey(), url, 20, TimeUnit.SECONDS);
		String[] strs = state.split(",");
		String url = (String)WxConnectContext.getInstance().getCache().get(strs[0]);
		if(StringUtils.isBlank(url)) {
			response.setStatus(400);
			response.setHeader("Connection", "close");
			return "";
		}
		StringBuilder redirect = new StringBuilder(strs[0]);
		
		String publicKey = strs[1];
		LOGGER.debug("redirectURL:"+redirect.toString());
		String openId = getOpenId(code, publicKey);
		String returnValue = verify(url,response,request,strs,openId);
		Long end = System.currentTimeMillis();
		LOGGER.debug("耗时："+(end-start)+"毫秒");
		return returnValue;	
	}

	protected String verify(String url, HttpServletResponse response, HttpServletRequest request, String[] strs, String openId){
		if (!IWxOauthService.OAUTH__SUCCESS.equals(request.getSession().getAttribute(IWxOauthService.OAUTH_KEY))) {
			synchronized (this) {
				if (!IWxOauthService.OAUTH__SUCCESS.equals(request.getSession().getAttribute(IWxOauthService.OAUTH_KEY))) {
					if(StringUtils.isNotBlank(openId)) {
						request.getSession().setAttribute(IWxOauthService.OAUTH_KEY,IWxOauthService.OAUTH__SUCCESS);
						request.getSession().setAttribute(OAUTH_IDENTIFY, openId);
						return sendRedirect(response,url);
					}else{
						LOGGER.error("Oauth授权不通过！");
						return "/NoPermission";
					}
				}else{
					return sendRedirect(response,url);
				}
			}
		}else{
			return sendRedirect(response,url);
		}
	}
	
	private String sendRedirect(HttpServletResponse response,String redirect){
		LOGGER.debug("转发地址："+redirect);
		response.setStatus(307);
		response.setHeader("Location", redirect);
		response.setHeader("Connection", "close");
		return null;
	}
	
	protected String getOpenId(String code, String publicKey){
		try{
			WxAccountEntry entry = WxConnectContext.getInstance().getAccountSupport().getAccount(publicKey);
			
			StringBuilder oauth2Url = new StringBuilder("https://api.weixin.qq.com/sns/oauth2/access_token?appid=");
			oauth2Url.append(entry.getAppid()).append("&secret=").append(entry.getSecret()).append("&code=").append(code);
			oauth2Url.append("&grant_type=authorization_code");
			
			LOGGER.debug("重定向地址:"+oauth2Url);
			String res = HttpsRequest.getCommonHttpServer().sendGet(oauth2Url.toString(),1);
			LOGGER.debug("腾讯返回信息:"+res);
			String openId = "";
			if (StringUtils.isBlank(res) || StringUtils.isBlank(openId = JSONObject.parseObject(res).getString("openid"))) {
				LOGGER.warn("微信OAuth2.0网页授权失败，url:"+oauth2Url);
			} else {
				LOGGER.debug("授权成功，openId====>"+openId);
			}
			return openId;
		}catch(Exception e){
			e.printStackTrace();
			LOGGER.error("授权过程出错", e);
			return "";
		}
	}
}
