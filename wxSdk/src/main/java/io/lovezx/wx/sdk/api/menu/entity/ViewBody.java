package io.lovezx.wx.sdk.api.menu.entity;

public class ViewBody extends TypeSupport {
    private String url;
    public ViewBody(){
        this.type = "view";
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    
}
