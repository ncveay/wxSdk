package io.lovezx.wx.sdk.api.message.kf.entity;

import java.util.List;

import io.lovezx.wx.sdk.api.AbstractResponse;

public class WxMessageKFListRequest extends AbstractResponse{
    
    private List<ListInfo> kf_list;
    
    public List<ListInfo> getKf_list() {
        return kf_list;
    }

    public void setKf_list(List<ListInfo> kf_list) {
        this.kf_list = kf_list;
    }

    public static class ListInfo{
        private String kf_account;
        private String kf_nick;
        private String kf_id;
        private String kf_headimgurl;
        public String getKf_account() {
            return kf_account;
        }
        public String getKf_nick() {
            return kf_nick;
        }
        public String getKf_id() {
            return kf_id;
        }
        public String getKf_headimgurl() {
            return kf_headimgurl;
        }
        public void setKf_account(String kf_account) {
            this.kf_account = kf_account;
        }
        public void setKf_nick(String kf_nick) {
            this.kf_nick = kf_nick;
        }
        public void setKf_id(String kf_id) {
            this.kf_id = kf_id;
        }
        public void setKf_headimgurl(String kf_headimgurl) {
            this.kf_headimgurl = kf_headimgurl;
        }
    }
}
