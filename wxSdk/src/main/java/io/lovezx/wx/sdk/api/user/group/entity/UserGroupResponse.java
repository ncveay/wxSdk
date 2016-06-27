package io.lovezx.wx.sdk.api.user.group.entity;

import io.lovezx.wx.sdk.api.AbstractResponse;

public class UserGroupResponse extends AbstractResponse {
    private Group group;
    
    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }
}
