package io.lovezx.wx.sdk.api.message.kf.entity;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.api.MediaIDEntry;
import io.lovezx.wx.sdk.api.message.kf.AbstractMessageRequest;
/**
 * 发送客服音频消息
 * @author yuanyi
 *
 */
public class VoiceMessageRequest extends AbstractMessageRequest {

	private static final long serialVersionUID = -8436292786269677285L;
	
	private MediaIDEntry voice;
    
    public VoiceMessageRequest(){
        this.msgtype = "voice";
    }
    
    public MediaIDEntry getVoice() {
        return voice;
    }
    public void setVoice(MediaIDEntry voice) {
        this.voice = voice;
    }
    
    @Override
    public boolean checkValid(){
        if(!super.checkValid()) return false;
        if(voice == null) return false;
        return StringUtils.isNotEmpty(voice.getMedia_id());
    }
}
