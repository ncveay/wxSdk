package io.lovezx.wx.sdk.receive;

import io.lovezx.wx.sdk.api.pay.xml.Node;
import io.lovezx.wx.sdk.api.pay.xml.XmlMap;

public class EventKeyReceive extends ReceiveResource {

	private static final long serialVersionUID = 1L;
	private String key;

    public String getKey() {
        return key;
    }
    void setKey(String key) {
        this.key = key;
    }
    @Override
    public XmlMap toXml(){
    	XmlMap map = super.toXml();
    	map.addNode(new Node("key", key));
    	return map;
    }
}
