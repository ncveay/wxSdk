package io.lovezx.wx.sdk.receive;

import java.io.Serializable;

import io.lovezx.wx.sdk.api.pay.xml.Node;
import io.lovezx.wx.sdk.api.pay.xml.XmlMap;

public class ReceiveResource extends Resource  implements Serializable{
    
	private static final long serialVersionUID = 1L;
	private String msgId;
    public String getMsgId() {
        return msgId;
    }
    protected void setMsgId(String msgId) {
        this.msgId = msgId;
    }
    
    @Override
    public XmlMap toXml(){
    	XmlMap map = super.toXml();
    	map.addNode(new Node("msgId", msgId));
    	return map;
    }
}
