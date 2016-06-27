package io.lovezx.wx.sdk.api.message.groupSend.entity;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.api.message.groupSend.GroupSendSupport;
import io.lovezx.wx.sdk.api.message.kf.entity.TextMessageRequest.Text;
/**
 * 群发文本
 * @author yuanyi
 *
 */
public class TextGroupSendRequest extends GroupSendSupport{

	private static final long serialVersionUID = 2814828654509484566L;
	
	private Text text;
    public TextGroupSendRequest(){
        this.msgtype = "text";
    }
    
    public Text getText() {
        return text;
    }
    public void setText(Text text) {
        this.text = text;
    }
    
    @Override
    public boolean checkValid(){
        if(!super.checkValid()) return false;
        if(text == null) return false;
        return StringUtils.isNotEmpty(text.getContent());
    }
}
