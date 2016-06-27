package io.lovezx.wx.sdk.receive;

import io.lovezx.wx.sdk.api.pay.xml.Node;
import io.lovezx.wx.sdk.api.pay.xml.XmlMap;

public class LocationReceive extends ReceiveResource {

	private static final long serialVersionUID = 1L;
	LocationReceive(){}
	
    private String location_x;
    private String location_y;
    private String scale;
    private String label;
    /**
     * 纬度
     * @return
     */
    public String getLocation_x() {
        return location_x;
    }
    /**
     * 经度
     * @return
     */
    public String getLocation_y() {
        return location_y;
    }
    /**
     * 缩放大小
     * @return
     */
    public String getScale() {
        return scale;
    }
    /**
     * 位置信息
     * @return
     */
    public String getLabel() {
        return label;
    }
    void setLocation_x(String location_x) {
        this.location_x = location_x;
    }
    void setLocation_y(String location_y) {
        this.location_y = location_y;
    }
    void setScale(String scale) {
        this.scale = scale;
    }
    void setLabel(String label) {
        this.label = label;
    }
    
    @Override
    public XmlMap toXml(){
    	XmlMap map = super.toXml();
    	map.addNode(new Node("location_x", location_x));
    	map.addNode(new Node("location_y", location_y));
    	map.addNode(new Node("scale", scale));
    	map.addNode(new Node("label", label));
    	return map;
    }
}
