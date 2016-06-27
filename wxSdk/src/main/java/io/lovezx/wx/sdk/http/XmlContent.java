package io.lovezx.wx.sdk.http;

import io.lovezx.wx.sdk.resource.ResourceLoader;

public class XmlContent implements PostContent {
    
    private static final ResourceLoader loader = new ResourceLoader();
    
    public String toString(){
        return loader.beanToXml(this);
    }
}
