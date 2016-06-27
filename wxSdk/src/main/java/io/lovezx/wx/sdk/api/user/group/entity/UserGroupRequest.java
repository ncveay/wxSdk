package io.lovezx.wx.sdk.api.user.group.entity;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.SelfValid;
import io.lovezx.wx.sdk.http.JSONContent;

public class UserGroupRequest extends JSONContent implements SelfValid {
    private GroupName group;
    public static class GroupName {
        private String name;
        public GroupName(String name){
        	this.name = name;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
    public GroupName getGroup() {
        return group;
    }
    public void setGroup(GroupName group) {
        this.group = group;
    }
    public boolean checkValid() {
        if(group==null) return false;
        if(StringUtils.isEmpty(group.name)) return false;
        if(group.name.length()>30) return false;
        return true;
    }
    
}
