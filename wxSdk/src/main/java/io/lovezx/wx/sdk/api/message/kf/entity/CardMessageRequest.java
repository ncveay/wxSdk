package io.lovezx.wx.sdk.api.message.kf.entity;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.api.message.kf.AbstractMessageRequest;

/**
 * 发送客服卡券消息
 * @author yuanyi
 *
 */
public class CardMessageRequest extends AbstractMessageRequest {

	private static final long serialVersionUID = 7347693938329021536L;
	private Card wxcard;
    
    public Card getWxcard() {
        return wxcard;
    }
    public void setWxcard(Card wxcard) {
        this.wxcard = wxcard;
    }

    @Override
    public boolean checkValid(){
        if(!super.checkValid()) return false;
        if(wxcard == null) return false;
        if(StringUtils.isEmpty(wxcard.card_id)) return false;
        return StringUtils.isNotEmpty(wxcard.card_ext);
    }
    
    public static class Card implements Serializable{
    	
    	private static final long serialVersionUID = 5205519351191514555L;
		
    	private String card_id;
        private String card_ext;
        
        public String getCard_id() {
            return card_id;
        }
        public String getCard_ext() {
            return card_ext;
        }
        public void setCard_id(String card_id) {
            this.card_id = card_id;
        }
        public void setCard_ext(String card_ext) {
            this.card_ext = card_ext;
        }
    }
}
