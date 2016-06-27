package io.lovezx.wx.sdk.api.message.template.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.SelfValid;
import io.lovezx.wx.sdk.http.JSONContent;

public class TemplateSendRequest extends JSONContent implements SelfValid {
    private String touser;
    private String template_id;
    private String url;
    private String topcolor;
    private Map<String, KeyNote> data = new LinkedHashMap<String, KeyNote>();
    
    public boolean checkValid() {
        if(StringUtils.isEmpty(touser)) return false;
        if(StringUtils.isEmpty(template_id)) return false;
        //first 和 remark  至少有2项
        return (data.size()>2);
    }
    public String getTouser() {
        return touser;
    }
    public String getTemplate_id() {
        return template_id;
    }
    public String getUrl() {
        return url;
    }
    public Map<String, KeyNote> getData() {
        return data;
    }
    public void setTouser(String touser) {
        this.touser = touser;
    }
    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setData(Map<String, KeyNote> data) {
        this.data = data;
    }
    public TemplateSendRequest addKeyNote(String key, KeyNote note){
        data.put(key, note);
        return this;
    }

    public String getTopcolor() {
		return topcolor;
	}
	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}


	public static class KeyNote{
        private String value;
        private String color;
        public KeyNote(String value, String color){
            this.value = value;
            this.color = color;
        }
        public String getValue() {
            return value;
        }
        public String getColor() {
            return color;
        }
        public void setValue(String value) {
            this.value = value;
        }
        public void setColor(String color) {
            this.color = color;
        }
    }
}
