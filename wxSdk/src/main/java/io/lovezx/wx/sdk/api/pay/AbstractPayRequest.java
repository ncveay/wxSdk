package io.lovezx.wx.sdk.api.pay;

import io.lovezx.wx.sdk.api.pay.xml.Node;
import io.lovezx.wx.sdk.api.pay.xml.XmlMap;

public abstract class AbstractPayRequest extends AbstractRequest{
    
    private String appid;
    
    protected XmlMap getXmlMap(){
        XmlMap map = super.getXmlMap();
        map.addNode(new Node("appid", appid));
        return map;
    }

    protected String getAppid() {
		return appid;
	}

    protected void setAppid(String appid) {
		this.appid = appid;
	}
    
}
