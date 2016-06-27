package io.lovezx.wx.sdk.api.pay;

import io.lovezx.wx.sdk.Assert;
import io.lovezx.wx.sdk.SdkUtil;
import io.lovezx.wx.sdk.api.APIRequestSupport;
import io.lovezx.wx.sdk.api.RequestParameter;
import io.lovezx.wx.sdk.cache.CacheContants;
import io.lovezx.wx.sdk.context.APIContants;
import io.lovezx.wx.sdk.context.WxConnectContext;
import io.lovezx.wx.sdk.context.account.WxAccountEntry;
/**
 * 支付API
 * @author yuanyi
 *D
 */
public class WxPayAPI extends APIRequestSupport{

    /**
     * 下单
     * @param pay
     * @return
     */
    public OrderPayResponse order(PayOrder order){
    	String appid = getAppid();
    	order.setAppid(appid);
    	WxAccountEntry account = (WxAccountEntry)WxConnectContext.getInstance().getCache().get(CacheContants.ACCOUNT_.name() + getAppid());
    	String mch_id = account.getMchid();
    	order.setMch_id(mch_id);
    	
    	Assert.isValid(order);
        OrderPayResponse res = sendPayRequest(APIContants.PAY_ORDER_UNIFIED, order, SdkUtil.createEmptyList(RequestParameter.class), OrderPayResponse.class);
        return res;
    }
    
    /**
     * 退款
     * @param order
     * @return
     */
    public RefundPayResponse refund(RefundOrder order){
        order.setOp_user_id(order.getMch_id());
        Assert.isValid(order);
        return sendPayRequest(APIContants.PAY_ORDER_REFUND, order, SdkUtil.createEmptyList(RequestParameter.class), RefundPayResponse.class);
    }
    
    /**
     * 查询订单
     * @param order
     * @return
     */
    public QueryOrderResponse select(QueryOrder order){
        Assert.isValid(order);
        return sendPayRequest(APIContants.PAY_ORDER_QUERY, order, SdkUtil.createEmptyList(RequestParameter.class), QueryOrderResponse.class);
    }
}
