package io.lovezx.wx.sdk.receive;

import io.lovezx.wx.sdk.api.pay.xml.Node;
import io.lovezx.wx.sdk.api.pay.xml.XmlMap;

public class TextReceive extends ReceiveResource {
    
	private static final long serialVersionUID = 1L;

	TextReceive(){}
	
    private String content;

    public String getContent() {
        return content;
    }
    void setContent(String content) {
        this.content = content;
    }
    @Override
    public XmlMap toXml(){
    	XmlMap map = super.toXml();
    	map.addNode(new Node("content", content));
    	return map;
    }
}
