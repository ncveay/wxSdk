package io.lovezx.wx.sdk.receive;

import io.lovezx.wx.sdk.api.pay.xml.Node;
import io.lovezx.wx.sdk.api.pay.xml.XmlMap;

public class VoiceResource extends Resource {

   private String mediaId;
   
   public VoiceResource(String mediaId){
       this.setMsgType("voice");
       this.mediaId = mediaId;
   }

    public String getMediaId() {
        return mediaId;
    }
    void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
    
    private static class VoiceNode extends Node{

        public VoiceNode(String value) {
            super("voice", value);
        }
        
        public String toString(){
            return "<Voice><MediaId>"+this.getValue()+"</MediaId></Voice>";
        }
    }
    public XmlMap toXml(){
        XmlMap map = super.toXml();
        map.addNode(new VoiceNode(this.getMediaId()));
        return map;
    }
}
