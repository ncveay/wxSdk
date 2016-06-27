package io.lovezx.wx.sdk.receive;

import java.util.ArrayList;
import java.util.List;

import io.lovezx.wx.sdk.api.message.kf.entity.NewsMessageRequest;
import io.lovezx.wx.sdk.api.pay.xml.Node;
import io.lovezx.wx.sdk.api.pay.xml.XmlMap;

public class NewsResource extends Resource {
    
    private List<NewsMessageRequest.Article> articles = new ArrayList<NewsMessageRequest.Article>();
    
    public NewsResource addArticle(NewsMessageRequest.Article article){
        articles.add(article);
        setMsgType("news");
        return this;
    }
    
    public XmlMap toXml(){
        XmlMap map = super.toXml();
        NewsNode node = new NewsNode();
        for(NewsMessageRequest.Article article : articles){
            node.articles.add(new ArticleNode(
                    article.getTitle(), article.getDescription(), article.getPicurl(), article.getUrl()));
        }
        map.addNode(node);
        map.addNode(new Node("ArticleCount", articles.size()+""));
        return map;
    }
    
    public List<NewsMessageRequest.Article> getArticles() {
        return articles;
    }

    private static class NewsNode extends Node{
        
        private List<ArticleNode> articles = new ArrayList<NewsResource.ArticleNode>();
        
        public NewsNode() {
            super("Article", "Article");
        }
        
        public String toString(){
            StringBuilder builder = new StringBuilder("<Articles>");
            for(ArticleNode node : articles){
                builder.append(node);
            }
            builder.append("</Articles>");
            return builder.toString();
        }
    }
    
    private static class ArticleNode extends Node{
        public ArticleNode(String title, String desc, String picUrl, String url) {
        	super("article", "article");
        	this.title = title;
        	this.desc = desc;
        	this.picUrl = picUrl;
        	this.url = url;
        }
        private String title = "";
        private String desc = "";
        private String picUrl = "";
        private String url = "";
        
        public String toString(){
            return "<item>" +
                    "<Title><![CDATA["+title+"]]></Title>" +
                    "<Description><![CDATA["+desc+"]]></Description>" +
                    "<PicUrl><![CDATA["+picUrl+"]]></PicUrl>" +
                    "<Url><![CDATA["+url+"]]></Url>" +
                    "</item>";
        }
    }
}
