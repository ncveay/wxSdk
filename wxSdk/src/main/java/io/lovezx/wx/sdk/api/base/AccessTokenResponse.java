package io.lovezx.wx.sdk.api.base;

import io.lovezx.wx.sdk.api.AbstractResponse;

public class AccessTokenResponse extends AbstractResponse{
    
    private String access_token = "";
    
    private int expires_in;

    public String getAccessToken() {
        return access_token;
    }

    public int getExpireTime() {
        return expires_in;
    }
    
}
