package io.lovezx.wx.sdk.api.message.groupSend;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 接口请使用以下子类<br>
 * @see com.cfwx.rox.weixin.sdk.api.message.groupSend.entity.CardOpenIdSendRequest 卡券
 * @see com.cfwx.rox.weixin.sdk.api.message.groupSend.entity.ImageOpenIdSendRequest 图片
 * @see com.cfwx.rox.weixin.sdk.api.message.groupSend.entity.NewsOpenIdSendRequest 图文
 * @see com.cfwx.rox.weixin.sdk.api.message.groupSend.entity.TextOpenIdSendRequest 文本
 * @see com.cfwx.rox.weixin.sdk.api.message.groupSend.entity.VideoOpenIdSendRequest 视频
 * @see com.cfwx.rox.weixin.sdk.api.message.groupSend.entity.VoiceOpenIdSendRequest 音频
 * @author yuanyi
 *
 */
public abstract class OpenIdSendSupport extends MsgTypeSupport{
    
	private static final long serialVersionUID = 6982067540124811937L;

	private static final Logger LOGGER = LoggerFactory.getLogger(OpenIdSendSupport.class);
    
    public List<String> touser;

    public List<String> getTouser() {
        return touser;
    }
    public void setTouser(List<String> touser) {
        this.touser = touser;
    }
    
    @Override
    public boolean checkValid() {
       if(!super.checkValid()) return false;
       if(touser == null || touser.size()<2 || touser.size()>1000) {
           LOGGER.error("接收人数量错误！必须大于等于2个且小于等于1000个； size:"+(touser==null? "null" :touser.size()));
           return false;
       }
       return true;
    }
    
}
