package io.lovezx.wx.sdk.api.message.groupSend.entity;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.api.message.groupSend.GroupSendSupport;
/**
 * 群发卡券
 * @author yuanyi
 *
 */
public class CardGroupSendRequest extends GroupSendSupport{

	private static final long serialVersionUID = -3532011878571445615L;
	private Card wxcard;

    public CardGroupSendRequest(){
        this.msgtype = "wxcard";
    }
    public Card getCard() {
        return wxcard;
    }

    public void setCard(Card card) {
        this.wxcard = card;
    }
    
    public static class Card {
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
