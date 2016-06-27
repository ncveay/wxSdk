package io.lovezx.wx.sdk.api.message.groupSend;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.SelfValid;
import io.lovezx.wx.sdk.http.JSONContent;

public class MsgTypeSupport  extends JSONContent implements SelfValid,Serializable{
    
	private static final long serialVersionUID = 20160607L;
	
	protected String msgtype;

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public boolean checkValid() {
        return StringUtils.isNotEmpty(msgtype);
    }
}
