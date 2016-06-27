package io.lovezx.wx.sdk.receive;

import io.lovezx.wx.sdk.api.pay.xml.Node;
import io.lovezx.wx.sdk.api.pay.xml.XmlMap;

public class LinkReceive extends ReceiveResource {
    
	private static final long serialVersionUID = 1L;

	LinkReceive(){}
	
    private String title;
    private String description;
    private String url;
    
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getUrl() {
        return url;
    }
    void setTitle(String title) {
        this.title = title;
    }
    void setDescription(String description) {
        this.description = description;
    }
    void setUrl(String url) {
        this.url = url;
    }
    @Override
    public XmlMap toXml(){
    	XmlMap map = super.toXml();
    	map.addNode(new Node("title", title));
    	map.addNode(new Node("description", description));
    	map.addNode(new Node("url", url));
    	return map;
    }
}
