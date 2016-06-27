package io.lovezx.wx.sdk.api;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.SelfValid;
import io.lovezx.wx.sdk.http.JSONContent;

public class OpenIdRequest extends JSONContent implements SelfValid{
    private String openid;

    public OpenIdRequest(String openid){
        this.openid = openid;
    }
    
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public boolean checkValid() {
        return StringUtils.isNotEmpty(openid);
    }
    
}
