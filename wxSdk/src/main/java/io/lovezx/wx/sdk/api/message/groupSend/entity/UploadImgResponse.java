package io.lovezx.wx.sdk.api.message.groupSend.entity;

import io.lovezx.wx.sdk.api.AbstractResponse;
/**
 * 上传图片回复
 * @author yuanyi
 *
 */
public class UploadImgResponse extends AbstractResponse {
    private String url;

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
