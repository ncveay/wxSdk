package io.lovezx.wx.sdk.api.message;

public class MessageStatusResponse extends MessageIdSupport{
    private static final String SUCCESS = "SEND_SUCCESS";
    private String msg_status;
    public String getMsg_status() {
        return msg_status;
    }
    public void setMsg_status(String msg_status) {
        this.msg_status = msg_status;
    }
    
    public boolean hasSuccess(){
        return SUCCESS.equals(msg_status);
    }
    
}
