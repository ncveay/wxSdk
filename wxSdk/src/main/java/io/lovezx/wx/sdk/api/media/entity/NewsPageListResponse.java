package io.lovezx.wx.sdk.api.media.entity;

import java.util.List;

import io.lovezx.wx.sdk.api.AbstractResponse;
import io.lovezx.wx.sdk.api.media.entity.NewsRequest.Article;
/**
 * 永久图文列表回复--分页
 * @author yuanyi
 *
 */
public class NewsPageListResponse extends AbstractResponse {
    private int total_count;
    private int item_count;
    private List<NewsItem> item;
    
    public int getTotal_count() {
        return total_count;
    }
    public int getItem_count() {
        return item_count;
    }
    public List<NewsItem> getItem() {
        return item;
    }
    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }
    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }
    public void setItem(List<NewsItem> item) {
        this.item = item;
    }
    /**
     * 表示一个图文消息
     * @author yuanyi
     *
     */
    public static class NewsItem {
        private String media_id;
        private NewsContent content;
        private long update_time;
        
        public String getMedia_id() {
            return media_id;
        }
        public NewsContent getContent() {
            return content;
        }
        public long getUpdate_time() {
            return update_time;
        }
        public void setMedia_id(String media_id) {
            this.media_id = media_id;
        }
        public void setContent(NewsContent content) {
            this.content = content;
        }
        public void setUpdate_time(long update_time) {
            this.update_time = update_time;
        }
    }
    /**
     * 图文内容
     * @author yuanyi
     *
     */
    public static class NewsContent{
        private List<Article> news_item;

        public List<Article> getNews_item() {
            return news_item;
        }
        public void setNews_item(List<Article> news_item) {
            this.news_item = news_item;
        }
    }
}
