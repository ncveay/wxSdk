package io.lovezx.wx.sdk.api.pay.xml;

import java.util.ArrayList;
import java.util.List;

public class XmlMap {
    
    private List<Node> nodeList = new ArrayList<Node>();
    
    public boolean addNode(Node node){
        return nodeList.add(node);
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder("<xml>");
        for(Node node : nodeList){
            sb.append(node.toString());
        }
        sb.append("</xml>");
        return sb.toString();
    }
}
