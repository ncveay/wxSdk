package io.lovezx.wx.sdk.api.media.entity;

import io.lovezx.wx.sdk.api.AbstractResponse;

public class MediaIdResponse extends AbstractResponse{
    
    private String media_id;
    /*上传图片时，才返回此值*/
    private String url;
    /**上传图片时，才返回此值*/
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getMedia_id() {
        return media_id;
    }
    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }
}
