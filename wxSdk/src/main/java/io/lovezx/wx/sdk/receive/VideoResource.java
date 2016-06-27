package io.lovezx.wx.sdk.receive;

import io.lovezx.wx.sdk.api.pay.xml.Node;
import io.lovezx.wx.sdk.api.pay.xml.XmlMap;

public class VideoResource extends Resource {

   private String video;
   private String description = "";
   private String title = "";
   
   public VideoResource(String mediaId){
       this.setMsgType("video");
       this.video = mediaId;
   }
       
   public String getMediaId() {
        return video;
    }
   public String getDescription() {
        return description;
    }
   public String getTitle() {
        return title;
    }
   void setMediaId(String MediaId) {
        this.video = MediaId;
    }
   public void setDescription(String description) {
        this.description = description;
    }
   public void setTitle(String title) {
        this.title = title;
    }


    private static class VideoNode extends Node{

        private String title;
        private String desc;
        
        public VideoNode(String mediaId, String title, String desc) {
            super("video", mediaId);
            this.title = title;
            this.desc = desc;
        }
        
        public String toString(){
            return "<Video><MediaId>"+this.getValue()+"</MediaId><Title><![CDATA["+title+"]]></Title><Description><![CDATA["+desc+"]]></Description></Video>";
        }
    }
    public XmlMap toXml(){
        XmlMap map = super.toXml();
        map.addNode(new VideoNode(this.getMediaId(), this.getTitle(), this.getDescription()));
        return map;
    }
}
