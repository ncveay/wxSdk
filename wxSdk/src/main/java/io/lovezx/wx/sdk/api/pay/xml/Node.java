package io.lovezx.wx.sdk.api.pay.xml;

public class Node {
    private String name;
    private String value;
    
    public Node(String name, String value){
        this.name = name;
        this.value = value;
    }
    
    protected String getName() {
        return name;
    }
    protected String getValue() {
        return value;
    }
    protected void setName(String name) {
        this.name = name;
    }
    protected void setValue(String value) {
        this.value = value;
    }
    public String toString(){
        return "<"+name+"><![CDATA["+value+"]]></"+name+">";
    }
}
