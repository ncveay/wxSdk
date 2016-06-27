package io.lovezx.wx.sdk.api.base;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import io.lovezx.wx.sdk.SdkUtil;
import io.lovezx.wx.sdk.api.APIRequestSupport;
import io.lovezx.wx.sdk.api.RequestParameter;
import io.lovezx.wx.sdk.cache.CacheContants;
import io.lovezx.wx.sdk.context.APIContants;
import io.lovezx.wx.sdk.context.WxConnectContext;
import io.lovezx.wx.sdk.context.account.WxAccountEntry;
/**
 * 获取access_token的接口
 * @author yuanyi
 *
 */
public class WxAccessTokenAPI extends APIRequestSupport{
    
    private static Logger LOGGER = LoggerFactory.getLogger(WxAccessTokenAPI.class);
    
    /**
     * 只能返回正确的accessToken或者抛出异常
     * @return
     */
    public String getAccessToken(){
        String token = null;
        try {
            WxConnectContext context = WxConnectContext.getInstance();
            
            String url = context.getApiContant(APIContants.BASE_ACCESS_TOKEN);
            WxAccountEntry entry = (WxAccountEntry)context.getCache().get(CacheContants.ACCOUNT_.name()+this.getAppid());
            url = String.format(url, this.getAppid(), entry.getSecret());
            String response = context.getCommonHttpServer().sendGet(url,1);
            LOGGER.debug("获取access_token ===>appid:"+this.getAppid()+"; secret:"+entry.getSecret()); 
            if(StringUtils.isEmpty(response)){
                LOGGER.error("获取不到微信端信息，网络连接原因？或是微信服务出错？");
                throw new IllegalStateException("获取不到微信端信息，网络连接原因？或是微信服务出错？");
            }
            token = JSON.parseObject(response).getString("access_token");
            LOGGER.error("请求access_token返回消息：" + response);
        } catch (Exception e) {
            throw new IllegalStateException("connect wx server fail", e);
        }
        if(StringUtils.isEmpty(token)) throw new IllegalStateException("access_token无法获取");
        return token;
    }
    
    /**
     * 获取微信服务IP地址列表
     * @return
     */
    public List<String> getWxServerIPList(){
        ServerListResponse resp = sendRequest(APIContants.WX_SERVER_IP_LIST, SdkUtil.createEmptyList(RequestParameter.class), ServerListResponse.class);
        return resp.getIp_list();
    }

}
