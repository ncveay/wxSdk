package io.lovezx.wx.sdk.oauth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class OauthFilter implements Filter {
	

	public void destroy() {
	} 

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		arg2.doFilter(new OpRequestWrap((HttpServletRequest) arg0),arg1);   
	}      

	public void init(FilterConfig arg0) throws ServletException {
	}



}
