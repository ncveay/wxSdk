package io.lovezx.wx.sdk.api.user.entity;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.SelfValid;
import io.lovezx.wx.sdk.http.JSONContent;

public class UserRemarkRequest extends JSONContent implements SelfValid {
    private String openid;
    private String remark;
    public String getOpenid() {
        return openid;
    }
    public String getRemark() {
        return remark;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public boolean checkValid() {
        if(StringUtils.isEmpty(openid)) return false;
        if(StringUtils.isEmpty(remark)) return false;
        if(remark.length() > 30) return false;
        return true;
    }
}
