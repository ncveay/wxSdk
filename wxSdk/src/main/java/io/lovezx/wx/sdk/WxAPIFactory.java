package io.lovezx.wx.sdk;

import io.lovezx.wx.sdk.api.base.WxAccessTokenAPI;
import io.lovezx.wx.sdk.api.base.WxJsAPI;
import io.lovezx.wx.sdk.api.media.WxMediaAPI;
import io.lovezx.wx.sdk.api.menu.WxMenuAPI;
import io.lovezx.wx.sdk.api.message.groupSend.WxMessageGroupSendAPI;
import io.lovezx.wx.sdk.api.message.kf.WxMessageKFAPI;
import io.lovezx.wx.sdk.api.message.template.WxMessageTempAPI;
import io.lovezx.wx.sdk.api.pay.WxPayAPI;
import io.lovezx.wx.sdk.api.pay.redPackage.WxRedPacketApi;
import io.lovezx.wx.sdk.api.user.WxUserAPI;
import io.lovezx.wx.sdk.api.user.group.WxUserGroupAPI;
import io.lovezx.wx.sdk.api.user.qrcode.QrcodeAPI;
import io.lovezx.wx.sdk.context.WxConnectContext;
import io.lovezx.wx.sdk.context.account.WxAccountEntry;

public class WxAPIFactory {
    
    public static boolean addNewAccount(WxAccountEntry entry){
        WxConnectContext.getInstance().getCache().put("acount_"+entry.getAppid(), entry);
        return true;    
    }
    
    public static WxJsAPI getJsApi(String appid){
    	WxJsAPI api = new WxJsAPI();
    	api.SetAppid(appid);  
        return api;    
    }
    
    public static WxPayAPI getPayApi(String appid){
        WxPayAPI api = new WxPayAPI();
        api.SetAppid(appid);
        return api;
    }
    
    /**
     * 红包
     * @param appid
     * @return
     */
    public static WxRedPacketApi getRedPacketApi(String appid){
    	WxRedPacketApi api = new WxRedPacketApi();
        api.SetAppid(appid);
        return api;
    }
    
    /**
     * accessToken， 服务器列表 API
     * @return
     */
    public static WxAccessTokenAPI getAccessTokenApi(String appid){
        WxAccessTokenAPI api = new WxAccessTokenAPI();
        api.SetAppid(appid);
        return api;
    }
    /**
     * 客服相关API
     * @return
     */
    public static WxMessageKFAPI getKFApi(String appid){
        WxMessageKFAPI api = new WxMessageKFAPI();
        api.SetAppid(appid);
        return api;
    }
    /**
     * 素材相关API
     * @return
     */
    public static WxMediaAPI getMediaApi(String appid){
        WxMediaAPI api = new WxMediaAPI();
        api.SetAppid(appid);
        return api;
    }
    /**
     * 菜单相关API
     * @return
     */
    public static WxMenuAPI getMenuApi(String appid){
        WxMenuAPI api = new WxMenuAPI();
        api.SetAppid(appid);
        return api;
    }
    /**
     * 群发相关API
     * @return
     */
    public static WxMessageGroupSendAPI getGroupSendApi(String appid){
        WxMessageGroupSendAPI api = new WxMessageGroupSendAPI();
        api.SetAppid(appid);
        return api;
    }
    /**
     * 模板消息API
     * @return
     */
    public static WxMessageTempAPI getTemplateMessageApi(String appid){
        WxMessageTempAPI api = new WxMessageTempAPI();
        api.SetAppid(appid);
        return api;
    }
    /**
     * 用户API
     * @return
     */
    public static WxUserAPI getUserApi(String appid){
        WxUserAPI api = new WxUserAPI();
        api.SetAppid(appid);
        return api;
    }
    /**
     * 用户分组API
     * @return
     */
    public static WxUserGroupAPI getUserGroupApi(String appid){
    	WxUserGroupAPI api = new WxUserGroupAPI();
        api.SetAppid(appid);
        return api;
    }
    
    /**
     * 二维码API
     * @param appid
     * @return
     */
    public static QrcodeAPI getQrcodeAPI(String appid){
    	QrcodeAPI api = new QrcodeAPI();
    	api.SetAppid(appid);
    	return api;
    }
}
