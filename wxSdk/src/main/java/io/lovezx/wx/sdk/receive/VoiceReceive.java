package io.lovezx.wx.sdk.receive;

import io.lovezx.wx.sdk.api.pay.xml.Node;
import io.lovezx.wx.sdk.api.pay.xml.XmlMap;

public class VoiceReceive extends ReceiveResource {
	
	private static final long serialVersionUID = 1L;
	VoiceReceive(){}
	
    private String mediaId;
    private String format;
    private String Recognition;
    public String getMediaId() {
        return mediaId;
    }
    public String getFormat() {
        return format;
    }
    public String getRecognition() {
        return Recognition;
    }
    void setRecognition(String recognition) {
        Recognition = recognition;
    }
    void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
    void setFormat(String format) {
        this.format = format;
    }
    @Override
    public XmlMap toXml(){
    	XmlMap map = super.toXml();
    	map.addNode(new Node("mediaId", mediaId));
    	map.addNode(new Node("format", format));
    	map.addNode(new Node("Recognition", Recognition));
    	return map;
    }
    
}
