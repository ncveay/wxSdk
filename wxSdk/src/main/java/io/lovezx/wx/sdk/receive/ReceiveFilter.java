package io.lovezx.wx.sdk.receive;

/**
 * 拦截器
 * @author yuanyi
 *
 */
public interface ReceiveFilter {
    
     String getName();
    
     void preHandler(ReceiveContext context) throws Exception;
    
     Resource postHandler(Resource resource, ReceiveContext context) throws Exception;
}
