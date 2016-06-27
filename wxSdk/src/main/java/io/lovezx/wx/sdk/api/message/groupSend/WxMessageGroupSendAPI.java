package io.lovezx.wx.sdk.api.message.groupSend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.lovezx.wx.sdk.Assert;
import io.lovezx.wx.sdk.SdkUtil;
import io.lovezx.wx.sdk.api.APIRequestSupport;
import io.lovezx.wx.sdk.api.AbstractResponse;
import io.lovezx.wx.sdk.api.RequestParameter;
import io.lovezx.wx.sdk.api.message.MessageIdSupport;
import io.lovezx.wx.sdk.api.message.MessageStatusResponse;
import io.lovezx.wx.sdk.api.message.groupSend.entity.GroupSendResponse;
import io.lovezx.wx.sdk.api.message.groupSend.entity.UploadImgResponse;
import io.lovezx.wx.sdk.api.message.groupSend.entity.VideoGroupSendRequest;
import io.lovezx.wx.sdk.api.message.groupSend.entity.VideoOpenIdSendRequest;
import io.lovezx.wx.sdk.api.message.kf.AbstractMessageRequest;
import io.lovezx.wx.sdk.context.APIContants;
import io.lovezx.wx.sdk.http.JSONContent;
/**
 * 群发接口
 * @author yuanyi
 *
 */
public class WxMessageGroupSendAPI extends APIRequestSupport {
    private static final Logger LOGGER = LoggerFactory.getLogger(WxMessageGroupSendAPI.class);
    
    /**
     * 上传群发图文图片
     */
    public UploadImgResponse uploadImg(byte[] content, String fileName){
    	Assert.notNull(fileName, "文件名不能为空");
    	return sendRequestFormData(APIContants.MESSAGE_GS_UPLOAD_IMG, content, fileName, SdkUtil.createEmptyList(RequestParameter.class), UploadImgResponse.class);
    }
    
    /**
     * 分组群发<br>
     * <code>
     * TextGroupSendRequest tgsr = new TextGroupSendRequest();<br>
     * tgsr.setText(new Text("你好"));<br>
     * Filter filter = new Filter();<br>
     * filter.setGroup_id("a group id");<br>
     * filter.setIs_to_all(true);//true 如果需要将此消息做为历史消息<br>
     * tgsr.setFilter(filter);<br>
     * sendAllByGroup(tgsr);<br>
     * <code>
     * @see com.cfwx.rox.weixin.sdk.api.message.groupSend.entity.CardGroupSendRequest 卡券
     * @see com.cfwx.rox.weixin.sdk.api.message.groupSend.entity.ImageGroupSendRequest 图片
     * @see com.cfwx.rox.weixin.sdk.api.message.groupSend.entity.NewsGroupSendRequest 图文
     * @see com.cfwx.rox.weixin.sdk.api.message.groupSend.entity.TextGroupSendRequest 文本
     * @see com.cfwx.rox.weixin.sdk.api.message.groupSend.entity.VideoGroupSendRequest 视频
     * @see com.cfwx.rox.weixin.sdk.api.message.groupSend.entity.VoiceGroupSendRequest 音频
     * */
    public GroupSendResponse sendAllByGroup(GroupSendSupport gsBody){
        Assert.isValid(gsBody);
        changeMedia(gsBody);
        return sendRequest(APIContants.MESSAGE_GS_SEND_ALL, gsBody, SdkUtil.createEmptyList(RequestParameter.class), GroupSendResponse.class);
    }
    /**
     * 根据OpenId群发<br>
     * <code>
     * TextOpenIdSendRequest tosr = new TextOpenIdSendRequest();<br>
     * tgsr.setText(new Text("你好"));<br>
     * tosr.setTouser(Arrays.asList(new String[]{"openid1","openid2","openid3"}));<br>
     * sendAllByOpenId(tosr);<br>
     * <code>
     * @see com.cfwx.rox.weixin.sdk.api.message.groupSend.entity.CardOpenIdSendRequest 卡券
     * @see com.cfwx.rox.weixin.sdk.api.message.groupSend.entity.ImageOpenIdSendRequest 图片
     * @see com.cfwx.rox.weixin.sdk.api.message.groupSend.entity.NewsOpenIdSendRequest 图文
     * @see com.cfwx.rox.weixin.sdk.api.message.groupSend.entity.TextOpenIdSendRequest 文本
     * @see com.cfwx.rox.weixin.sdk.api.message.groupSend.entity.VideoOpenIdSendRequest 视频
     * @see com.cfwx.rox.weixin.sdk.api.message.groupSend.entity.VoiceOpenIdSendRequest 音频
     * */
    public GroupSendResponse sendAllByOpenId(OpenIdSendSupport opBody){
        Assert.isValid(opBody);
        changeMedia(opBody);
        return sendRequest(APIContants.MESSAGE_GS_SEND_OPENDID, opBody, SdkUtil.createEmptyList(RequestParameter.class), GroupSendResponse.class);
    }
    /**
     * delete message
     * @param opBody
     * @return
     */
    public AbstractResponse delMessage(String msgId){
        Assert.notNull(msgId, "msgId can not be null!");
        return sendRequest(APIContants.MESSAGE_GS_MESSAGE_DEL, new MSGID(msgId), SdkUtil.createEmptyList(RequestParameter.class), AbstractResponse.class);
    }
    /**
     * send preview message
     * @see com.cfwx.rox.weixin.sdk.api.message.kf.entity.CardMessageRequest CardMessageBody_卡券
     * @see com.cfwx.rox.weixin.sdk.api.message.kf.entity.ImageMessageRequest ImageMessageBody_图片
     * @see com.cfwx.rox.weixin.sdk.api.message.kf.entity.NewsMessageRequest NewsMessageBody_图文
     * @see com.cfwx.rox.weixin.sdk.api.message.kf.entity.TextMessageRequest TextMessageBody_文本
     * @see com.cfwx.rox.weixin.sdk.api.message.kf.entity.VideoMessageRequest VideoMessageBody_视频
     * @see com.cfwx.rox.weixin.sdk.api.message.kf.entity.VoiceMessageRequest VoiceMessageBody_音频
     * @param message
     * @return
     */
    public MessageIdSupport sendPreView(AbstractMessageRequest message){
        Assert.isValid(message);
        return sendRequest(APIContants.MESSAGE_GS_MESSAGE_PREVIEW, message, SdkUtil.createEmptyList(RequestParameter.class), MessageIdSupport.class);
    }
    /**
     * get message send status
     * @param msgId
     * @return
     */
    public MessageStatusResponse getMessageStatus(String msgId){
        Assert.notNull(msgId, "msgId can not be null!");
        return sendRequest(APIContants.MESSAGE_GS_MESSAGE_GETSTATUS, new MSGID(msgId), SdkUtil.createEmptyList(RequestParameter.class), MessageStatusResponse.class);
    }
    
    @SuppressWarnings("unused")
    private static class MSGID extends JSONContent{
        public MSGID(String msgId){
            this.msg_id = msgId;
        }
        private String msg_id;
        public String getMsg_id() {
            return msg_id;
        }
        public void setMsg_id(String msg_id) {
            this.msg_id = msg_id;
        }
    }
    
    private void changeMedia(OpenIdSendSupport obj){
        if(!VideoOpenIdSendRequest.class.isInstance(obj)){
            return;
        }
        VideoOpenIdSendRequest video = (VideoOpenIdSendRequest)obj;
        VideoSpecialRequest body = new VideoSpecialRequest();
        body.setMedia_id(video.getVideo().getMedia_id());
        VideoSpecialResponse response = sendRequest(APIContants.MESSAGE_GS_GET_VIDEO, body, SdkUtil.createEmptyList(RequestParameter.class), VideoSpecialResponse.class);
        if(response.hasError()){
            LOGGER.error("发送视频消息时出现错误， 可能为media_id错误; media_id："+video.getVideo().getMedia_id());
            throw new IllegalArgumentException("发送视频消息时出现错误， 可能为media_id错误");
        }
        video.getVideo().setMedia_id(response.getMedia_id());
    }
    
    private void changeMedia(GroupSendSupport obj){
        if(!VideoGroupSendRequest.class.isInstance(obj)){
            return;
        }
        VideoGroupSendRequest video = (VideoGroupSendRequest)obj;
        VideoSpecialRequest body = new VideoSpecialRequest();
        body.setMedia_id(video.getMpvideo().getMedia_id());
        VideoSpecialResponse response = sendRequest(APIContants.MESSAGE_GS_GET_VIDEO, body, SdkUtil.createEmptyList(RequestParameter.class), VideoSpecialResponse.class);
        if(response.hasError()){
            LOGGER.error("发送视频消息时出现错误， 可能为media_id错误; media_id："+video.getMpvideo().getMedia_id());
            throw new IllegalArgumentException("发送视频消息时出现错误， 可能为media_id错误");
        }
        video.getMpvideo().setMedia_id(response.getMedia_id());
    }
}
