package io.lovezx.wx.sdk.api.user.entity;

import java.util.List;

import io.lovezx.wx.sdk.api.AbstractResponse;

public class UserOpenIdListResponse extends AbstractResponse{
    private int total;
    private int count;
    private OpenIdList data;
    private String next_openid;
    
    public int getTotal() {
        return total;
    }

    public int getCount() {
        return count;
    }

    public OpenIdList getData() {
        return data;
    }

    public String getNext_openid() {
        return next_openid;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setData(OpenIdList data) {
        this.data = data;
    }

    public void setNext_openid(String next_openid) {
        this.next_openid = next_openid;
    }

    public static class OpenIdList{
        private List<String> openid;

        public List<String> getOpenid() {
            return openid;
        }
        public void setOpenid(List<String> openid) {
            this.openid = openid;
        }
    }
}
