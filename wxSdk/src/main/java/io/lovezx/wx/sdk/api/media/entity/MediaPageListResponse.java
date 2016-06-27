package io.lovezx.wx.sdk.api.media.entity;

import java.util.List;

import io.lovezx.wx.sdk.api.AbstractResponse;

public class MediaPageListResponse extends AbstractResponse {
    private int total_count;
    private int item_count;
    private List<MediaItem> item;
    /**此类型总共的素材量*/
    public int getTotal_count() {
        return total_count;
    }
    /**本次调用获取的素材量*/
    public int getItem_count() {
        return item_count;
    }
    public List<MediaItem> getItem() {
        return item;
    }
    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }
    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }
    public void setItem(List<MediaItem> item) {
        this.item = item;
    }

    public static class MediaItem{
        private String media_id;
        private String name;
        private long update_time;
        private String url;
        
        public String getMedia_id() {
            return media_id;
        }
        public String getName() {
            return name;
        }
        public long getUpdate_time() {
            return update_time;
        }
        public String getUrl() {
            return url;
        }
        public void setMedia_id(String media_id) {
            this.media_id = media_id;
        }
        public void setName(String name) {
            this.name = name;
        }
        public void setUpdate_time(long update_time) {
            this.update_time = update_time;
        }
        public void setUrl(String url) {
            this.url = url;
        }
    }
}
