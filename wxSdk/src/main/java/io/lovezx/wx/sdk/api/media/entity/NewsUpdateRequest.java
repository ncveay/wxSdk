package io.lovezx.wx.sdk.api.media.entity;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.SelfValid;
import io.lovezx.wx.sdk.api.media.entity.NewsRequest.Article;
import io.lovezx.wx.sdk.http.JSONContent;

public class NewsUpdateRequest extends JSONContent implements SelfValid{
    private String media_id;
    private int index;
    private Article articles;
    
    public boolean checkValid() {
        if(!articles.checkValid()) return false;
        if(StringUtils.isEmpty(media_id)) return false;
        return true;
    }
    
    public String getMedia_id() {
        return media_id;
    }
    public int getIndex() {
        return index;
    }
    public Article getArticles() {
        return articles;
    }
    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public void setArticles(Article articles) {
        this.articles = articles;
    }
}
