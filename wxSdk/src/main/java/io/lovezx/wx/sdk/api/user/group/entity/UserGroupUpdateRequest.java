package io.lovezx.wx.sdk.api.user.group.entity;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.SelfValid;
import io.lovezx.wx.sdk.http.JSONContent;

public class UserGroupUpdateRequest extends JSONContent implements SelfValid{
    private Group group;
    
    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }
    public boolean checkValid() {
        if(group==null) return false;
        if(group.getId()<0) return false;
        if(StringUtils.isEmpty(group.getName())) return false;
        if(group.getName().length() > 30) return false;
        return true;
    }
}
