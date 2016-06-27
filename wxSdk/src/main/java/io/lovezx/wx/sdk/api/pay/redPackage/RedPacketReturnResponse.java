package io.lovezx.wx.sdk.api.pay.redPackage;

import java.util.Map;

import io.lovezx.wx.sdk.api.pay.AbstractPayResponse;

public class RedPacketReturnResponse extends AbstractPayResponse{
    public void setField(Map<String, String> map){
        super.setField(map);
        result_code=map.get("result_code");
        err_code = map.get("err_code");
        err_code_des = map.get("err_code_des");
    }

    private String result_code;
    private String err_code;
    private String err_code_des;
    
	public String getResult_code() {
		return result_code;
	}
	protected void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getErr_code() {
		return err_code;
	}
	protected void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public String getErr_code_des() {
		return err_code_des;
	}
	protected void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
}
