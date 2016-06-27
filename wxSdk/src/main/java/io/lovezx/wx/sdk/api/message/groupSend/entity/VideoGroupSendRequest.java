package io.lovezx.wx.sdk.api.message.groupSend.entity;

import io.lovezx.wx.sdk.api.MediaIDEntry;
import io.lovezx.wx.sdk.api.message.groupSend.GroupSendSupport;
/**
 * 群发视频
 * @author yuanyi
 *
 */
public class VideoGroupSendRequest extends GroupSendSupport{

	private static final long serialVersionUID = 3615504701116640809L;
	
	private MediaIDEntry mpvideo;

    public VideoGroupSendRequest(){
        this.msgtype = "mpvideo";
    }
    public MediaIDEntry getMpvideo() {
        return mpvideo;
    }

    public void setMpvideo(MediaIDEntry video) {
        this.mpvideo = video;
    }
    @Override
    public boolean checkValid() {
        if(!super.checkValid()) return false;
        if(mpvideo == null) return false;
        return mpvideo.checkValid();
    }
}
