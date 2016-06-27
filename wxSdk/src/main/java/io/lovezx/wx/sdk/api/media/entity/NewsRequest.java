package io.lovezx.wx.sdk.api.media.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.lovezx.wx.sdk.SelfValid;
import io.lovezx.wx.sdk.http.JSONContent;

/**
 * 图文文章
 * @author yuanyi
 *
 */
public class NewsRequest extends JSONContent implements SelfValid{
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsRequest.class);
    private List<Article> articles = new ArrayList<Article>();
    
    public boolean checkValid() {
        for(Article article : articles){
            if(!article.checkValid()){
                LOGGER.error("文章校验失败，文章名称："+article.getTitle());
                return false;
            } 
        }
        return true;
    }
    
    public List<Article> getArticles() {
        return articles;
    }
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public static class Article{
        /*标题*/
        private String title;
        /*缩略图ID*/
        private String thumb_media_id;
        /*作者*/
        private String author;
        /*摘要*/
        private String digest;
        /*是否显示封面*/
        private String show_cover_pic;
        /*消息内容*/
        private String content;
        /*点击“阅读原文”后的URL */
        private String content_source_url;
        
        public boolean checkValid() {
            if(StringUtils.isEmpty(title)) return false;
            if(StringUtils.isEmpty(thumb_media_id)) return false;
            if(StringUtils.isEmpty(author)) return false;
            if(StringUtils.isEmpty(show_cover_pic)) return false;
            if(!"1".equals(show_cover_pic) && !"0".equals(show_cover_pic)) return false;
            if(StringUtils.isEmpty(content)) return false;
            /*必须小于2万字*/
            if(content.length() > 20000) return false;
            return true;
        }
        
        public String getTitle() {
            return title;
        }
        public String getThumb_media_id() {
            return thumb_media_id;
        }
        public String getAuthor() {
            return author;
        }
        public String getDigest() {
            return digest;
        }
        public String getShow_cover_pic() {
            return show_cover_pic;
        }
        public String getContent() {
            return content;
        }
        public String getContent_source_url() {
            return content_source_url;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public void setThumb_media_id(String thumb_media_id) {
            this.thumb_media_id = thumb_media_id;
        }
        public void setAuthor(String author) {
            this.author = author;
        }
        public void setDigest(String digest) {
            this.digest = digest;
        }
        public void setShow_cover_pic(String show_cover_pic) {
            this.show_cover_pic = show_cover_pic;
        }
        public void setContent(String content) {
            this.content = content;
        }
        public void setContent_source_url(String content_source_url) {
            this.content_source_url = content_source_url;
        }
    }
}

