package io.lovezx.wx.sdk.receive;

import io.lovezx.wx.sdk.api.pay.xml.Node;
import io.lovezx.wx.sdk.api.pay.xml.XmlMap;

public class MusicResource extends Resource {
    
    private String title;
    private String desc;
    private String musicUrl;
    private String hqMusicUrl;
    private String thumbMediaId;
    
    public MusicResource(){
        this.setMsgType("music");
    }
    
    protected String getTitle() {
        return title;
    }
    protected String getDesc() {
        return desc;
    }
    protected String getMusicUrl() {
        return musicUrl;
    }
    protected String getHqMusicUrl() {
        return hqMusicUrl;
    }
    protected String getThumbMediaId() {
        return thumbMediaId;
    }
    protected void setTitle(String title) {
        this.title = title;
    }
    protected void setDesc(String desc) {
        this.desc = desc;
    }
    protected void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }
    protected void setHqMusicUrl(String hqMusicUrl) {
        this.hqMusicUrl = hqMusicUrl;
    }
    protected void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }
    
    private static class MusicNode extends Node{
        public MusicNode(String musicUrl) {
            super("music", musicUrl);
            this.musicUrl = musicUrl;
        }
        private String title;
        private String desc;
        private String musicUrl;
        private String hqMusicUrl;
        private String thumbMediaId;
        
        public String toString(){
            return "<Music>" +
            		"<Title>"+title+"</Title>" +
            		"<Description>"+desc+"</Description>" +
            		"<MusicUrl>"+musicUrl+"</MusicUrl>" +
            		"<HQMusicUrl>"+hqMusicUrl+"</HQMusicUrl>" +
            		"<ThumbMediaId>"+thumbMediaId+"</ThumbMediaId>" +
            		"</Music>";
        }
    }
    
    public XmlMap toXml(){
        XmlMap map = new XmlMap();
        MusicNode node = new MusicNode(this.getMusicUrl());
        node.title = this.getTitle();
        node.desc = this.getDesc();
        node.hqMusicUrl = this.getHqMusicUrl();
        node.thumbMediaId = this.getThumbMediaId();
        map.addNode(node);
        return map;
    }
   
    
}
