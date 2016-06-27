package io.lovezx.wx.sdk.api.pay.redPackage;

import io.lovezx.wx.sdk.Assert;
import io.lovezx.wx.sdk.SdkUtil;
import io.lovezx.wx.sdk.api.APIRequestSupport;
import io.lovezx.wx.sdk.api.RequestParameter;
import io.lovezx.wx.sdk.cache.CacheContants;
import io.lovezx.wx.sdk.context.APIContants;
import io.lovezx.wx.sdk.context.WxConnectContext;
import io.lovezx.wx.sdk.context.account.WxAccountEntry;

public class WxRedPacketApi extends APIRequestSupport{

	  /**
     * 发现金红包
     * @param order
     * @return
     */
    public RedPacketPayResponse giveOutRedPacket(RedPacket redPacket){
        WxAccountEntry account = (WxAccountEntry)WxConnectContext.getInstance().getCache().get(CacheContants.ACCOUNT_.name() + getAppid());
        redPacket.setMch_id(account.getMchid());
        redPacket.setWxappid(account.getAppid());
        Assert.isValid(redPacket);
        return sendPayRequest(APIContants.REDPACKET_PAY, redPacket, SdkUtil.createEmptyList(RequestParameter.class), RedPacketPayResponse.class);
    }
}
