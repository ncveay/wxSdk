package io.lovezx.wx.sdk.context.account;

import java.util.List;

/**
 * 针对维护账户操作的接口
 * @author yuanyi
 *
 */
public interface WxAccountSupport {
    
    /**返回系统中所有的微信公众号账户*/
    public List<WxAccountEntry> getAllAccount();
    
    public WxAccountEntry getAccount(String publicKey);
    
}
