package io.lovezx.wx.sdk.api.message.kf.entity;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.api.MediaIDEntry;
import io.lovezx.wx.sdk.api.message.kf.AbstractMessageRequest;
/**
 * 发送客服图片消息
 * @author yuanyi
 *
 */
public class ImageMessageRequest extends AbstractMessageRequest {
	private static final long serialVersionUID = 2326367661925597364L;
	private MediaIDEntry image;
    
    public ImageMessageRequest(){
        this.msgtype = "image";
    }
    
    public MediaIDEntry getImage() {
        return image;
    }
    public void setImage(MediaIDEntry image) {
        this.image = image;
    }
    
    @Override
    public boolean checkValid(){
        if(!super.checkValid()) return false;
        if(image == null) return false;
        return StringUtils.isNotEmpty(image.getMedia_id());
    }
}
