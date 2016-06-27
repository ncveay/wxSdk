package io.lovezx.wx.sdk.oauth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class description:
 * @author yuanyi
 * 2015-5-25 下午5:47:33
 */
public abstract class IWxOauthService {  
	public static final String OAUTH_IDENTIFY = "OAUTHID";
	public static final Character FLAG_TRUE = 'a';
	public static final String ILLEGAL_FLAG = "-1";  
	public static final String OAUTH_KEY = "OAUTHID";
	public static final String OAUTH__SUCCESS = "1";
	public static final String OAUTH_FAIL = "0";
	public static final String OAUTH_UNKNOW = "-1";
	public abstract String doVerify(HttpServletResponse response, HttpServletRequest request,String code,String state);
	
	public static String getOpenId(HttpServletRequest request){
		return (String)request.getSession().getAttribute(IWxOauthService.OAUTH_IDENTIFY);
	}
}
