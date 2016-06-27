package io.lovezx.wx.sdk.api.user.entity;

import io.lovezx.wx.sdk.api.AbstractResponse;

public class UserInfoResponse extends AbstractResponse {
    
    private int subscribe;
    private String openid;
    private String nickname;
    private int sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private long subscribe_time;
    private String unionid;
    private String remark;
    private int groupid;
    
    public static enum Sex {
        MALE, FEMLE, UNKNOW
    }
    public boolean hasSubscribe(){
        return subscribe!=0;
    }
    public Sex getSexEnum(){
        if(sex==2) return Sex.FEMLE;
        if(sex==1) return Sex.MALE;
        return Sex.UNKNOW;
    }
    public int getSubscribe() {
        return subscribe;
    }
    public String getOpenid() {
        return openid;
    }
    public String getNickname() {
        return nickname;
    }
    public int getSex() {
        return sex;
    }
    public String getLanguage() {
        return language;
    }
    public String getCity() {
        return city;
    }
    public String getProvince() {
        return province;
    }
    public String getCountry() {
        return country;
    }
    public String getHeadimgurl() {
        return headimgurl;
    }
    public long getSubscribe_time() {
        return subscribe_time;
    }
    public String getUnionid() {
        return unionid;
    }
    public String getRemark() {
        return remark;
    }
    public int getGroupid() {
        return groupid;
    }
    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }
    public void setSubscribe_time(long subscribe_time) {
        this.subscribe_time = subscribe_time;
    }
    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }
}
