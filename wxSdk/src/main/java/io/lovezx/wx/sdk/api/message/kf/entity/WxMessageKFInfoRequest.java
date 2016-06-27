package io.lovezx.wx.sdk.api.message.kf.entity;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.SelfValid;
import io.lovezx.wx.sdk.http.JSONContent;
/**
 * 客服信息
 * @author yuanyi
 *
 */
public class WxMessageKFInfoRequest extends JSONContent implements SelfValid{

    private String kf_account;
    private String nickname;
    private String password;
    
    public String getKf_account() {
        return kf_account;
    }
    public String getNickname() {
        return nickname;
    }
    public String getPassword() {
        return password;
    }
    public void setKf_account(String kf_account) {
        this.kf_account = kf_account;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean checkValid() {
        if(StringUtils.isEmpty(kf_account)) return false;
        return StringUtils.isNotEmpty(nickname);
    }
}
