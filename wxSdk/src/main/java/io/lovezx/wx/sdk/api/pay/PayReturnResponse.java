package io.lovezx.wx.sdk.api.pay;

import java.util.Map;

public class PayReturnResponse extends AbstractPayResponse{
    public void setField(Map<String, String> map){
        super.setField(map);
        appid = map.get("appid");
        mch_id = map.get("mch_id");
        sub_appid = map.get("sub_appid");
        sub_mch_id = map.get("sub_mch_id");
        nonce_str = map.get("nonce_str");
        sign = map.get("sign");
        err_code = map.get("err_code");
        err_code_des = map.get("err_code_des");
    }
    private String appid;
    
    private String mch_id;
    
    private String sub_appid;
    
    private String sub_mch_id;
    
    private String nonce_str;
    
    private String sign;
    
    private String err_code;

    private String err_code_des;
    
    public String getAppid() {
        return appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public String getSub_appid() {
        return sub_appid;
    }

    public String getSub_mch_id() {
        return sub_mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public String getSign() {
        return sign;
    }

    protected void setAppid(String appid) {
        this.appid = appid;
    }

    protected void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    protected void setSub_appid(String sub_appid) {
        this.sub_appid = sub_appid;
    }

    protected void setSub_mch_id(String sub_mch_id) {
        this.sub_mch_id = sub_mch_id;
    }

    protected void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    protected void setSign(String sign) {
        this.sign = sign;
    }

    public String getErr_code() {
        return err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    protected void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }
    
}
