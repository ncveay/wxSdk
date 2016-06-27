package io.lovezx.wx.sdk.api.message.kf.entity;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.api.message.kf.AbstractMessageRequest;
/**
 * 发送客服音乐消息
 * @author yuanyi
 *
 */
public class MusicMessageRequest extends AbstractMessageRequest {
	private static final long serialVersionUID = -2029937336179305929L;
	private Music music;

    public Music getMusic() {
        return music;
    }
    public void setMusic(Music music) {
        this.music = music;
    }

    @Override
    public boolean checkValid(){
        if(!super.checkValid()) return false;
        if(music == null) return false;
        if(StringUtils.isEmpty(music.musicurl)) return false;
        if(StringUtils.isEmpty(music.hqmusicurl)) return false;
        return StringUtils.isNotEmpty(music.thumb_media_id);
    }

    public static class Music implements Serializable{

		private static final long serialVersionUID = -4892966831364723591L;
		
		private String title;
        private String description;
        private String musicurl;
        private String hqmusicurl;
        private String thumb_media_id;
        
        public String getTitle() {
            return title;
        }
        public String getDescription() {
            return description;
        }
        public String getMusicurl() {
            return musicurl;
        }
        public String getHqmusicurl() {
            return hqmusicurl;
        }
        public String getThumb_media_id() {
            return thumb_media_id;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public void setMusicurl(String musicurl) {
            this.musicurl = musicurl;
        }
        public void setHqmusicurl(String hqmusicurl) {
            this.hqmusicurl = hqmusicurl;
        }
        public void setThumb_media_id(String thumb_media_id) {
            this.thumb_media_id = thumb_media_id;
        }
    }
}
