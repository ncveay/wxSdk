package io.lovezx.wx.sdk.api.message.kf.entity;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.api.message.kf.AbstractMessageRequest;
/**
 * 发送客服视频消息
 * @author yuanyi
 *
 */
public class VideoMessageRequest extends AbstractMessageRequest {

	private static final long serialVersionUID = 7784526547182034453L;
	
	private Video video;
    public VideoMessageRequest(){
        this.msgtype = "video";
    }
    
    public Video getVideo() {
        return video;
    }
    public void setVideo(Video video) {
        this.video = video;
    }

    @Override
    public boolean checkValid(){
        if(!super.checkValid()) return false;
        if(video == null) return false;
        if(StringUtils.isEmpty(video.media_id)) return false;
        return StringUtils.isNotEmpty(video.thumb_media_id);
    }
    
    public static class Video implements Serializable{

		private static final long serialVersionUID = -4574040240900787799L;
		
		private String media_id;
        private String thumb_media_id;
        private String title;
        private String description;
        
        public String getMedia_id() {
            return media_id;
        }
        public String getThumb_media_id() {
            return thumb_media_id;
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
        public void setThumb_media_id(String thumb_media_id) {
            this.thumb_media_id = thumb_media_id;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public void setDescription(String description) {
            this.description = description;
        }
    }
}
