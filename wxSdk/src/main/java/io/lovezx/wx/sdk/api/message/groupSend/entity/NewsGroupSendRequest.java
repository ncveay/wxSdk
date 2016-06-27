package io.lovezx.wx.sdk.api.message.groupSend.entity;

import io.lovezx.wx.sdk.api.MediaIDEntry;
import io.lovezx.wx.sdk.api.message.groupSend.GroupSendSupport;
/**
 * 群发图文
 * @author yuanyi
 *
 */
public class NewsGroupSendRequest extends GroupSendSupport{

	private static final long serialVersionUID = 7657925262208140915L;
	
	private MediaIDEntry mpnews;

    public NewsGroupSendRequest(){
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
