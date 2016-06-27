package io.lovezx.wx.sdk.api.media.entity;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.SelfValid;
import io.lovezx.wx.sdk.http.JSONContent;

public class VideoUploadRequest extends JSONContent implements SelfValid{
    
    private String title;
    private String introduction;
    
    public boolean checkValid() {
        if(StringUtils.isEmpty(title)) return false;
        if(StringUtils.isEmpty(introduction)) return false;
        return true;
    }
    
    public String getTitle() {
        return title;
    }
    public String getIntroduction() {
        return introduction;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
