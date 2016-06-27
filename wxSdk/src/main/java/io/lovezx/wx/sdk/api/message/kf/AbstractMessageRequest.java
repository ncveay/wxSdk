package io.lovezx.wx.sdk.api.message.kf;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.SelfValid;
import io.lovezx.wx.sdk.http.JSONContent;

/**
 * 客服消息 子類如有必要，需要重寫 isValid 方法
 * @author yuanyi
 *
 */
public class AbstractMessageRequest extends JSONContent implements SelfValid,Serializable{
    private static final long serialVersionUID = 6348312011901977458L;
	
    protected String touser;
    protected String msgtype;
    protected Customservice customservice;
    
    public String getTouser() {
        return touser;
    }
    public String getMsgtype() {
        return msgtype;
    }
    /**
     * @param touser openID
     */
    public void setTouser(String touser) {
        this.touser = touser;
    }
    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }
    public Customservice getCustomservice() {
        return customservice;
    }
    /**指定发送消息的客服*/
    public void setCustomservice(Customservice customservice) {
        this.customservice = customservice;
    }
    public boolean checkValid(){
        if(StringUtils.isEmpty(touser)) return false;
        return StringUtils.isNotEmpty(msgtype);
    }
    
    public static class Customservice {
        private String kf_account;

        public String getKf_account() {
            return kf_account;
        }

        public void setKf_account(String kf_account) {
            this.kf_account = kf_account;
        }
    }
}
