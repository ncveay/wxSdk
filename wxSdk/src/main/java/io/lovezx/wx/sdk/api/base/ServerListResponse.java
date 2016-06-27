package io.lovezx.wx.sdk.api.base;

import java.util.List;

import io.lovezx.wx.sdk.api.AbstractResponse;
/**
 * IP列表请求返回对象
 * @author yuanyi
 *
 */
public class ServerListResponse extends AbstractResponse{
    /*ip列表*/
    private List<String> ip_list;

    public List<String> getIp_list() {
        return ip_list;
    }

    public void setIp_list(List<String> ip_list) {
        this.ip_list = ip_list;
    }
    
}
