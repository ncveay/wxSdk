package io.lovezx.wx.sdk.api.message.kf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.Assert;
import io.lovezx.wx.sdk.SdkUtil;
import io.lovezx.wx.sdk.api.APIRequestSupport;
import io.lovezx.wx.sdk.api.AbstractResponse;
import io.lovezx.wx.sdk.api.RequestParameter;
import io.lovezx.wx.sdk.api.message.kf.entity.WxMessageKFInfoRequest;
import io.lovezx.wx.sdk.api.message.kf.entity.WxMessageKFListRequest;
import io.lovezx.wx.sdk.context.APIContants;
import io.lovezx.wx.sdk.security.md5.MD5Util;
/**
 * 消息--客服接口
 * @author yuanyi
 *
 */
public class WxMessageKFAPI extends APIRequestSupport{
    /**
     * 添加客服
     * */
    
    public AbstractResponse addKF(WxMessageKFInfoRequest info){
        Assert.isValid(info);
        if(StringUtils.isNotBlank(info.getPassword())){
            info.setPassword(MD5Util.MD5(info.getPassword()));
        }
        return sendRequest(APIContants.MESSAGE_KF_ACCOUNT_ADD, info, SdkUtil.createEmptyList(RequestParameter.class), AbstractResponse.class);
    }
    /**
     * 修改客服
     * */
    public AbstractResponse updateKF(WxMessageKFInfoRequest info){
        Assert.isValid(info);
        if(StringUtils.isNotBlank(info.getPassword())){
            info.setPassword(MD5Util.MD5(info.getPassword()));
        }
        return sendRequest(APIContants.MESSAGE_KF_ACCOUNT_UPDATE, info, SdkUtil.createEmptyList(RequestParameter.class), AbstractResponse.class);
    }
    /**
     * 删除客服
     * */
    public AbstractResponse delKF(String kfAccount){
        Assert.notNull(kfAccount, "客服账号不能为空！");
        List<RequestParameter> params = 
        Arrays.asList(new RequestParameter[]{new RequestParameter("kf_account", kfAccount)});
        return sendRequest(APIContants.MESSAGE_KF_ACCOUNT_DEL, params, AbstractResponse.class);
    }
    /**
     * 客服列表
     * */
    public WxMessageKFListRequest listKF(){
       WxMessageKFListRequest list = sendRequest(APIContants.MESSAGE_KF_ACCOUNT_LIST, SdkUtil.createEmptyList(RequestParameter.class), WxMessageKFListRequest.class);
       return list;
    }
    /**
     * 设置客服头像
     * */
    public AbstractResponse setHead(byte[] content, String fileName, String kfAccount){
        Assert.notNull(kfAccount, "客服账号不能为空！");
        if(!fileName.endsWith(".jpg")){
            throw new IllegalArgumentException("需要jpg文件");
        }
        List<RequestParameter> params = new ArrayList<RequestParameter>();
        params.add(new RequestParameter("kf_account", kfAccount));
        return sendRequestFormData(APIContants.MESSAGE_KF_ACCOUNT_HEAD, content, fileName, params, AbstractResponse.class);
    }
    /**
     * 发送客服消息
     * @see com.cfwx.rox.weixin.sdk.api.message.kf.entity.CardMessageRequest CardMessageBody_卡券
     * @see com.cfwx.rox.weixin.sdk.api.message.kf.entity.ImageMessageRequest ImageMessageBody_图片
     * @see com.cfwx.rox.weixin.sdk.api.message.kf.entity.NewsMessageRequest NewsMessageBody_图文
     * @see com.cfwx.rox.weixin.sdk.api.message.kf.entity.TextMessageRequest TextMessageBody_文本
     * @see com.cfwx.rox.weixin.sdk.api.message.kf.entity.VideoMessageRequest VideoMessageBody_视频
     * @see com.cfwx.rox.weixin.sdk.api.message.kf.entity.VoiceMessageRequest VoiceMessageBody_音频
     * */
    public AbstractResponse sendKFMsg(AbstractMessageRequest message){
        Assert.isValid(message);
        return sendRequest(APIContants.MESSAGE_KF_ACCOUNT_SEND, message, SdkUtil.createEmptyList(RequestParameter.class), AbstractResponse.class);
    }
}
