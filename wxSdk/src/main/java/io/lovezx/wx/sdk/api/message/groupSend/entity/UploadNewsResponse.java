package io.lovezx.wx.sdk.api.message.groupSend.entity;

import io.lovezx.wx.sdk.api.AbstractResponse;
/**
 * 上传图文回复
 * @author yuanyi
 *
 */
public class UploadNewsResponse extends AbstractResponse {
    private String type;
    private String media_id;
    private long created_at;
    public String getType() {
        return type;
    }
    public String getMedia_id() {
        return media_id;
    }
    public long getCreated_at() {
        return created_at;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }
    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }
    
}
