package io.lovezx.wx.sdk.api.message.template.entity;

import io.lovezx.wx.sdk.api.AbstractResponse;

public class TemplateMsgIdResponse extends AbstractResponse {
    private long msgid;

    public long getMsgid() {
        return msgid;
    }
    public void setMsgid(long msgid) {
        this.msgid = msgid;
    }
    
}
