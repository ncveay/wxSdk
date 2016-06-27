package io.lovezx.wx.sdk.api.message.groupSend.entity;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.api.message.groupSend.OpenIdSendSupport;
/**
 * 群发视频
 * @author yuanyi
 *
 */
public class VideoOpenIdSendRequest extends OpenIdSendSupport{

	private static final long serialVersionUID = -6412348249408156737L;
	
	private Video video;

    public VideoOpenIdSendRequest(){
        this.msgtype = "video";
    }
    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
    @Override
    public boolean checkValid() {
        if(!super.checkValid()) return false;
        if(video == null) return false;
        return video.checkValid();
    }
    
    public static class Video implements Serializable{
        
    	private static final long serialVersionUID = -3494829285543339163L;
		
        private String media_id;
        private String title;
        private String description;
        
        public boolean checkValid(){
            return StringUtils.isNotEmpty(media_id);
        }
        
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
    }
}
