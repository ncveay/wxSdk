package io.lovezx.wx.sdk.api.user.group.entity;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.SelfValid;
import io.lovezx.wx.sdk.http.JSONContent;

public class UserGroupMoveRequest extends JSONContent implements SelfValid{
    private String openid;
    private int to_groupid;
    
    public String getOpenid() {
        return openid;
    }
    public int getTo_groupid() {
        return to_groupid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public void setTo_groupid(int to_groupid) {
        this.to_groupid = to_groupid;
    }
    public boolean checkValid() {
        if(StringUtils.isEmpty(openid)) return false;
        return true;
    }
}
