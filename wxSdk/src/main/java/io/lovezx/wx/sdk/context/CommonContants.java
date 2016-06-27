package io.lovezx.wx.sdk.context;

public enum CommonContants {
    /**调用微信服务异常时，重试次数*/
    CONNECT_RETRY_TIME,
    /**accessToken缓存时间*/
    ACCESS_TOKEN_EXPIRE_TIME,
    /**receive上下文缓存时间*/
    RECEIVE_CONTEXT_EXPIRE_TIME,
    /**刷新ACCESS_TOKEN的间隔时间*/
    FLUSH_ACCESS_TOKEN_INTERVAL_TIME
}
