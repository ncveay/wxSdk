package io.lovezx.wx.sdk.api.message.template.entity;

import io.lovezx.wx.sdk.api.AbstractResponse;

public class TemplateIDResponse extends AbstractResponse{
    private String template_id;

    public String getTemplate_id() {
        return template_id;
    }
    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }
    
}
