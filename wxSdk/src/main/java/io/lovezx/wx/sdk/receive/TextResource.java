package io.lovezx.wx.sdk.receive;

import io.lovezx.wx.sdk.api.pay.xml.Node;
import io.lovezx.wx.sdk.api.pay.xml.XmlMap;

public class TextResource extends Resource {
    private String content;

    public String getContent() {
        return content;
    }
    void setContent(String content) {
        this.content = content;
    }
    
    public TextResource(String content){
        this.setMsgType("text");
        this.content = content;
    }
    
    @Override
    public XmlMap toXml(){
        XmlMap map = super.toXml();
        map.addNode(new Node("Content", content));
        return map;
    }
}
