package io.lovezx.wx.sdk.receive;

import io.lovezx.wx.sdk.api.pay.xml.Node;
import io.lovezx.wx.sdk.api.pay.xml.XmlMap;

public class LocationReportReceive extends ReceiveResource {
    
	private static final long serialVersionUID = 1L;

	LocationReportReceive(){}
	
    private String location_x;
    private String location_y;
    
    private String precision;

    public String getLocation_x() {
        return location_x;
    }
    public String getLocation_y() {
        return location_y;
    }
    public String getPrecision() {
        return precision;
    }
    void setLocation_x(String loaction_x) {
        this.location_x = loaction_x;
    }
    void setLocation_y(String location_y) {
        this.location_y = location_y;
    }
    void setPrecision(String precision) {
        this.precision = precision;
    }
    @Override
    public XmlMap toXml(){
    	XmlMap map = super.toXml();
    	map.addNode(new Node("location_x", location_x));
    	map.addNode(new Node("location_y", location_y));
    	map.addNode(new Node("precision", precision));
    	return map;
    }
}
