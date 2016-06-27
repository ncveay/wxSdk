package io.lovezx.wx.sdk.api.message.groupSend.entity;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.api.message.groupSend.OpenIdSendSupport;
import io.lovezx.wx.sdk.api.message.kf.entity.TextMessageRequest.Text;
/**
 * 群发文本
 * @author yuanyi
 *
 */
public class TextOpenIdSendRequest extends OpenIdSendSupport{

	private static final long serialVersionUID = 6335183213536037450L;
	
	private Text text;
    public TextOpenIdSendRequest(){
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
