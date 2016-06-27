package io.lovezx.wx.sdk.api.message.groupSend.entity;

import java.io.Serializable;

import io.lovezx.wx.sdk.api.MediaIDEntry;
import io.lovezx.wx.sdk.api.message.groupSend.OpenIdSendSupport;
/**
 * 群发图片
 * @author yuanyi
 *
 */
public class ImageOpenIdSendRequest extends OpenIdSendSupport implements Serializable{

	private static final long serialVersionUID = 3384191356335318865L;
	
	private MediaIDEntry image;

    public ImageOpenIdSendRequest(){
        this.msgtype = "image";
    }
    public MediaIDEntry getImage() {
        return image;
    }

    public void setImage(MediaIDEntry image) {
        this.image = image;
    }
    @Override
    public boolean checkValid() {
        if(!super.checkValid()) return false;
        if(image == null) return false;
        return image.checkValid();
    }
}
