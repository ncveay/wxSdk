package io.lovezx.wx.sdk.api.message.groupSend.entity;

import io.lovezx.wx.sdk.api.MediaIDEntry;
import io.lovezx.wx.sdk.api.message.groupSend.GroupSendSupport;
/**
 * 群发音频消息
 * @author yuanyi
 *
 */
public class VoiceGroupSendRequest extends GroupSendSupport{

	private static final long serialVersionUID = -9142582738226622431L;
	
	private MediaIDEntry voice;

    public VoiceGroupSendRequest(){
        this.msgtype = "voice";
    }
    public MediaIDEntry getVoice() {
        return voice;
    }

    public void setVoice(MediaIDEntry voice) {
        this.voice = voice;
    }
    @Override
    public boolean checkValid() {
        if(!super.checkValid()) return false;
        if(voice == null) return false;
        return voice.checkValid();
    }
}
