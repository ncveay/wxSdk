package io.lovezx.wx.sdk.api.message.kf.entity;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.api.message.kf.AbstractMessageRequest;

/**
 * 文本消息
 * @author yuanyi
 *
 */
public class TextMessageRequest extends AbstractMessageRequest{

	private static final long serialVersionUID = 9209704656443596205L;
	private Text text;
    public TextMessageRequest(){
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
        return StringUtils.isNotEmpty(text.content);
    }
    
    public static class Text implements Serializable {
        
    	private static final long serialVersionUID = 20160607L;
		
    	private String content;
        
    	public Text(String content){
            this.content = content;
        }
        public String getContent() {
            return content;
        }
        public Text setContent(String content) {
            this.content = content;
            return this;
        }
    }
}
