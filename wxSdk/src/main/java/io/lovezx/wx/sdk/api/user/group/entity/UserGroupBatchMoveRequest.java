package io.lovezx.wx.sdk.api.user.group.entity;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.SelfValid;
import io.lovezx.wx.sdk.http.JSONContent;

public class UserGroupBatchMoveRequest extends JSONContent implements SelfValid{
    private List<String> openid_list;
    private int to_groupid;
    public List<String> getOpenid_list() {
        return openid_list;
    }
    public int getTo_groupid() {
        return to_groupid;
    }
    public void setOpenid_list(List<String> openid_list) {
        this.openid_list = openid_list;
    }
    public void setTo_groupid(int to_groupid) {
        this.to_groupid = to_groupid;
    }
    public boolean checkValid() {
        if(openid_list == null || openid_list.isEmpty()) return false;
        if(openid_list.size() > 50) return false;
        for(String openid : openid_list){
            if(StringUtils.isEmpty(openid)) return false;
        }
        return true;
    }
}
