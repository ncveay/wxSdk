package io.lovezx.wx.sdk.api.user.entity;

import java.util.List;

import io.lovezx.wx.sdk.api.AbstractResponse;

public class UserInfoListResponse extends AbstractResponse {
    private List<UserInfoResponse> user_list;

    public List<UserInfoResponse> getUser_list() {
        return user_list;
    }

    public void setUser_list(List<UserInfoResponse> user_list) {
        this.user_list = user_list;
    }
}
