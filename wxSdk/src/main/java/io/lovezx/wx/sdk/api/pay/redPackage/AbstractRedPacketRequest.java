package io.lovezx.wx.sdk.api.pay.redPackage;

import io.lovezx.wx.sdk.api.pay.AbstractRequest;
import io.lovezx.wx.sdk.api.pay.xml.Node;
import io.lovezx.wx.sdk.api.pay.xml.XmlMap;

public abstract class AbstractRedPacketRequest extends AbstractRequest{
    
    private String wxappid;
    
    protected XmlMap getXmlMap(){
        XmlMap map = super.getXmlMap();
        map.addNode(new Node("wxappid", wxappid));
        return map;
    }

    protected String getWxappid() {
		return wxappid;
	}

    protected void setWxappid(String wxappid) {
		this.wxappid = wxappid;
	}
    
    protected void setMch_id(String mch_id) {
    	super.setMch_id(mch_id);
    }

}
