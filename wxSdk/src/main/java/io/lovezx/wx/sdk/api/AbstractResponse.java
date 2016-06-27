package io.lovezx.wx.sdk.api;

/**
 * 微信回复
 * @author yuanyi
 *
 */
public class AbstractResponse {
    /*错误代码*/
    private int errcode;
    /*错误信息*/
    private String errmsg;
    
    public boolean hasError(){
        //不为0时，代表请求有错误
        return !"0".equals(errcode+"");
    }
    
    public int getErrcode(){
        return errcode;
    }
    
    public String getErrmsg(){
        return errmsg;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
