package io.lovezx.wx.sdk.receive;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.api.pay.xml.Node;
import io.lovezx.wx.sdk.api.pay.xml.XmlMap;

public class Resource{
    
	private String ToUserName;
    private String FromUserName;
    private String CreateTime;
    private String MsgType;
    
    public String getToUserName() {
        return ToUserName;
    }
    public String getFromUserName() {
        return FromUserName;
    }
    public String getCreateTime() {
    	if(StringUtils.isBlank(CreateTime)){
    		CreateTime = String.valueOf(new Date().getTime());
    	}
        return CreateTime;
    }
    public String getMsgType() {
        return MsgType;
    }
    protected void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }
    protected void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }
    protected void setCreateTime(String createTime) {
        CreateTime = createTime;
    }
    protected void setMsgType(String msgType) {
        MsgType = msgType;
    }
    
    public XmlMap toXml() {
        XmlMap map = new XmlMap();
        map.addNode(new Node("ToUserName", getToUserName()));
        map.addNode(new Node("FromUserName", getFromUserName()));
        map.addNode(new Node("CreateTime", getCreateTime()));
        map.addNode(new Node("MsgType", getMsgType()));
        return map;
    }
}
