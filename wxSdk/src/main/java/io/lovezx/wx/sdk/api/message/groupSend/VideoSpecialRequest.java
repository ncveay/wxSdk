package io.lovezx.wx.sdk.api.message.groupSend;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.SelfValid;
import io.lovezx.wx.sdk.http.JSONContent;

public class VideoSpecialRequest extends JSONContent implements SelfValid,Serializable {

	private static final long serialVersionUID = 4724599215256564064L;
	
	private String media_id;
    private String title;
    private String description;
    public String getMedia_id() {
        return media_id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean checkValid() {
        if(StringUtils.isEmpty(media_id)) return false;
        return true;
    }
}
