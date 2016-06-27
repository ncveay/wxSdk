package io.lovezx.wx.sdk.receive;

import io.lovezx.wx.sdk.api.pay.xml.Node;
import io.lovezx.wx.sdk.api.pay.xml.XmlMap;

public class VideoReceive extends ReceiveResource {
	
	private static final long serialVersionUID = 1L;

	VideoReceive(){}
	
    private String mediaId;
    private String ThumbMediaId;
    
    public String getMediaId() {
        return mediaId;
    }
    public String getThumbMediaId() {
        return ThumbMediaId;
    }
    void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
    void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }
    @Override
    public XmlMap toXml(){
    	XmlMap map = super.toXml();
    	map.addNode(new Node("mediaId", mediaId));
    	map.addNode(new Node("ThumbMediaId", ThumbMediaId));
    	return map;
    }
}
