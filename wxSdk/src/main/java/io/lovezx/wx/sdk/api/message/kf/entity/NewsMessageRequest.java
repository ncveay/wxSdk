package io.lovezx.wx.sdk.api.message.kf.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.lovezx.wx.sdk.api.message.kf.AbstractMessageRequest;
/**
 * 发送客服图文消息
 * @author yuanyi
 *
 */
public class NewsMessageRequest extends AbstractMessageRequest {
	
	private static final long serialVersionUID = -6506529266362857042L;
	private static final Logger LOGGER = LoggerFactory.getLogger(NewsMessageRequest.class);
    
    public NewsMessageRequest(){
        this.msgtype = "news";
    }
    
    
    private News news;
    
    public News getNews() {
        return news;
    }
    public void setNews(News news) {
        this.news = news;
    }
    
    @Override
    public boolean checkValid(){
        if(!super.checkValid()) return false;
        if(news == null) return false;
        if(news.articles.size()>10){
            LOGGER.error("图文的数量不能大于10，现在图文数量："+ news.articles.size());
            return false;
        }
        return true;
        
    }
    
    public static class News implements Serializable{
        
    	private static final long serialVersionUID = 20160607L;
		
        private List<Article> articles = new ArrayList<NewsMessageRequest.Article>();

        public List<Article> getArticles() {
            return articles;
        }
        public void setArticles(List<Article> articles) {
            this.articles = articles;
        }
        public void addArticle(Article article){
            articles.add(article);
        }
    }
    public static class Article implements Serializable{

		private static final long serialVersionUID = 8403485717716666998L;
		
		private String title;
        private String description;
        private String url;
        private String picurl;
        
        public String getTitle() {
            return title;
        }
        public String getDescription() {
            return description;
        }
        public String getUrl() {
            return url;
        }
        public String getPicurl() {
            return picurl;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public void setUrl(String url) {
            this.url = url;
        }
        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }
    }
}
