package io.lovezx.wx.sdk.receive;

import io.lovezx.wx.sdk.api.pay.xml.Node;
import io.lovezx.wx.sdk.api.pay.xml.XmlMap;

public class ImageReceive extends ReceiveResource {
    
	private static final long serialVersionUID = 1L;

	ImageReceive(){}
	
    private String PicUrl;
    private String mediaId;
    
    public String getPicUrl() {
        return PicUrl;
    }
    public String getMediaId() {
        return mediaId;
    }
    void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
    void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
    
    @Override
    public XmlMap toXml(){
    	XmlMap map = super.toXml();
    	map.addNode(new Node("PicUrl", PicUrl));
    	map.addNode(new Node("mediaId", mediaId));
    	return map;
    }
    
}
