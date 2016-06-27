package io.lovezx.wx.sdk.api.message;

import io.lovezx.wx.sdk.api.AbstractResponse;

public class MessageIdSupport extends AbstractResponse {
    private String msg_id;
    public String getMsg_id() {
        return msg_id;
    }
    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }
}
