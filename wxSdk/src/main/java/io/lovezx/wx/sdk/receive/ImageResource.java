package io.lovezx.wx.sdk.receive;

import io.lovezx.wx.sdk.api.pay.xml.Node;
import io.lovezx.wx.sdk.api.pay.xml.XmlMap;

public class ImageResource extends Resource {

    private String mediaId;
    
    public ImageResource(String mediaId){
       this.setMsgType("image");
       this.mediaId = mediaId;
    }

    public String getMediaId() {
        return mediaId;
    }
    void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
    private static class ImageNode extends Node{

        public ImageNode(String value) {
            super("image", value);
        }
        
        public String toString(){
            return "<Image><MediaId>"+this.getValue()+"</MediaId></Image>";
        }
    }
    public XmlMap toXml(){
        XmlMap map = super.toXml();
        map.addNode(new ImageNode(this.getMediaId()));
        return map;
    }
}
