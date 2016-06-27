package io.lovezx.wx.sdk.api.message.groupSend.entity;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.api.message.groupSend.OpenIdSendSupport;
/**
 * 群发卡券
 * @author yuanyi
 *
 */
public class CardOpenIdSendRequest extends OpenIdSendSupport implements Serializable{
    private static final long serialVersionUID = 1524725078534413770L;
	
    private Card wxcard;

    public CardOpenIdSendRequest(){
        this.msgtype = "wxcard";
    }
    public Card getCard() {
        return wxcard;
    }

    public void setCard(Card card) {
        this.wxcard = card;
    }
    
    public static class Card implements Serializable {

		private static final long serialVersionUID = 4557591261577735532L;
		
		private String card_id;

        public String getCard_id() {
            return card_id;
        }
        public void setCard_id(String card_id) {
            this.card_id = card_id;
        }
    }
    
    @Override
    public boolean checkValid() {
        if(!super.checkValid()) return false;
        if(wxcard == null) return false;
        return StringUtils.isNotEmpty(wxcard.card_id);
    }
}
