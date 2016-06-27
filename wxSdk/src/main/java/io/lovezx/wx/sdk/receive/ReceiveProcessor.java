package io.lovezx.wx.sdk.receive;

/**
 * 接收消息处理器
 * @author yuanyi
 *
 */
public interface ReceiveProcessor {
    
    Resource onText(ReceiveContext context);
    
    Resource onImage(ReceiveContext context);
    
    Resource onVoice(ReceiveContext context);
    
    Resource onVideo(ReceiveContext context);
    
    Resource onLocation(ReceiveContext context);
    
    Resource onLink(ReceiveContext context);
    
    Resource onShortVideo(ReceiveContext context);
    
    Resource onSubscribe(ReceiveContext context);
    
    Resource onUnSubscribe(ReceiveContext context);
    
    Resource onScan(ReceiveContext context);
    
    Resource onLocationReport(ReceiveContext context);
    
    Resource onClick(ReceiveContext context);
    
    Resource onView(ReceiveContext context);
    
    Resource onDKFclose(ReceiveContext context);

	Resource onDKFcreate(ReceiveContext context);

	Resource endMassSendJob(ReceiveContext context);
}
