package io.lovezx.wx.sdk.api.user.group.entity;

import java.util.List;

import io.lovezx.wx.sdk.api.AbstractResponse;

public class UserGroupListResponse extends AbstractResponse{

    private List<GroupListEntry> groups;
    
    public List<GroupListEntry> getGroups() {
        return groups;
    }
    public void setGroups(List<GroupListEntry> groups) {
        this.groups = groups;
    }

    public static class GroupListEntry{
        private int id;
        private String name;
        private int count;
        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public int getCount() {
            return count;
        }
        public void setId(int id) {
            this.id = id;
        }
        public void setName(String name) {
            this.name = name;
        }
        public void setCount(int count) {
            this.count = count;
        }
    }
    
}
