package io.lovezx.wx.sdk.oauth;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import io.lovezx.wx.sdk.cache.CacheContants;
import io.lovezx.wx.sdk.context.WxConnectContext;

public class OpRequestWrap extends HttpServletRequestWrapper {
	
	
	   
    public OpRequestWrap(HttpServletRequest request) {
        super(request);                             
    }    
        
   
    /**
     * 重写getParameter方法     
     *
     * @param name   
     * @return   
     */
    public String getParameter(String name) {
        String value = super.getParameter(name);   
        if(name.equals("openId") && needOauth()){      
     		Object id = getSession().getAttribute(IWxOauthService.OAUTH_IDENTIFY);
     		if(id != null){   
     			return (String)id;  
     		}
     		
     	}
        return value;
    }
    
    private boolean needOauth(){
    	Object oauth = WxConnectContext.getInstance().getCache().get(CacheContants.GLOBAL_OAUTH_SWITCH.name());
		boolean oauthSwitch = false;
		if(oauth instanceof String){
			oauthSwitch = Boolean.valueOf((String)oauth);
		}else if(oauth instanceof Boolean){
			oauthSwitch = (Boolean)oauth;
		}else{    
			oauthSwitch = true;
		}
		return oauthSwitch;
    }
 
    /**
     *
     * @param name
     * @return
     */
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if(name.equals("openId") && needOauth()){
     		Object id = getSession().getAttribute(IWxOauthService.OAUTH_IDENTIFY);
     		if(id != null){
     			return new String[]{(String)id};
     		}
     	}
        if (values != null) {
        	
            for (int i = 0; i < values.length; i++) {
                values[i] = values[i];
            }
            
        }
        return values;
    }
 
    /**
     * @return
     */
    public Map<String, String[]> getParameterMap() {
    	Map<String, String[]> paramMap =  super.getParameterMap();
    	Map<String, String[]> newparamMap = new HashMap<String, String[]>(); 
        for (Iterator<Entry<String, String[]>> iterator = paramMap.entrySet().iterator(); iterator.hasNext(); ) {
            Entry<String, String[]> entry = iterator.next();
            String[] values = entry.getValue();
                newparamMap.put(entry.getKey(), values);
        }
        Object id = getSession().getAttribute(IWxOauthService.OAUTH_IDENTIFY);
        if(id!=null && needOauth()){
        	newparamMap.put("openId", new String[]{(String)id});
        }
        return newparamMap;
    }
}