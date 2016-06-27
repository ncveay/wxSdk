package io.lovezx.wx.sdk.api.message.groupSend.entity;

import java.io.Serializable;

import io.lovezx.wx.sdk.api.MediaIDEntry;
import io.lovezx.wx.sdk.api.message.groupSend.OpenIdSendSupport;
/**
 * 群发图文
 * @author yuanyi
 *
 */
public class NewsOpenIdSendRequest extends OpenIdSendSupport implements Serializable{
    
	private static final long serialVersionUID = 20160607L;
	
    private MediaIDEntry mpnews;

    public NewsOpenIdSendRequest(){
        this.msgtype = "mpnews";
    }
    public MediaIDEntry getMpnews() {
        return mpnews;
    }

    public void setMpnews(MediaIDEntry mpnews) {
        this.mpnews = mpnews;
    }
    @Override
    public boolean checkValid() {
        if(!super.checkValid()) return false;
        if(mpnews == null) return false;
        return mpnews.checkValid();
    }
}
