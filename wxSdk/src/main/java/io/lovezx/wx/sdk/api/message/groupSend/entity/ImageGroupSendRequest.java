package io.lovezx.wx.sdk.api.message.groupSend.entity;

import io.lovezx.wx.sdk.api.MediaIDEntry;
import io.lovezx.wx.sdk.api.message.groupSend.GroupSendSupport;
/**
 * 群发图片
 * @author yuanyi
 *
 */
public class ImageGroupSendRequest extends GroupSendSupport{

	private static final long serialVersionUID = -6330437847553234896L;
	
	private MediaIDEntry image;

    public ImageGroupSendRequest(){
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
