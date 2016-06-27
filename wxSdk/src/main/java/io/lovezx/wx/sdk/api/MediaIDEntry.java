package io.lovezx.wx.sdk.api;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.SelfValid;
import io.lovezx.wx.sdk.http.JSONContent;

public class MediaIDEntry extends JSONContent implements SelfValid,Serializable{

	private static final long serialVersionUID = 201606071L;
	
	private String media_id;

    public MediaIDEntry(String mediaId){
        this.media_id = mediaId;
    }
    
    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }
    
    public boolean checkValid(){
        return StringUtils.isNotEmpty(media_id);
    }
}
