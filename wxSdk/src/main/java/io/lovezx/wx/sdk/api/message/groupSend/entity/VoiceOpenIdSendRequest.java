package io.lovezx.wx.sdk.api.message.groupSend.entity;

import io.lovezx.wx.sdk.api.MediaIDEntry;
import io.lovezx.wx.sdk.api.message.groupSend.OpenIdSendSupport;
/**
 * 群发音频消息
 * @author yuanyi
 *
 */
public class VoiceOpenIdSendRequest extends OpenIdSendSupport{

	private static final long serialVersionUID = -7891151137538599537L;
	
	private MediaIDEntry voice;

    public VoiceOpenIdSendRequest(){
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
