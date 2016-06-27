package io.lovezx.wx.sdk.api.pay.redPackage;

import java.util.Map;

public class RedPacketPayResponse extends RedPacketReturnResponse{
    public void setField(Map<String, String> map){
        super.setField(map);
        mch_billno = map.get("mch_billno");
        mch_id = map.get("mch_id");
        wxappid = map.get("wxappid");
        re_openid = map.get("re_openid");
        total_amount = map.get("total_amount");
        send_time = map.get("send_time");
        send_listid = map.get("send_listid");
    }
    private String mch_billno;
    
    private String mch_id;
    
    private String wxappid;
    
    private String re_openid;
    
    private String total_amount;
    
    private String send_time;
    
    private String send_listid;

	public String getMch_billno() {
		return mch_billno;
	}

	public void setMch_billno(String mch_billno) {
		this.mch_billno = mch_billno;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getWxappid() {
		return wxappid;
	}

	public void setWxappid(String wxappid) {
		this.wxappid = wxappid;
	}

	public String getRe_openid() {
		return re_openid;
	}

	public void setRe_openid(String re_openid) {
		this.re_openid = re_openid;
	}

	public String getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}

	public String getSend_time() {
		return send_time;
	}

	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}

	public String getSend_listid() {
		return send_listid;
	}

	public void setSend_listid(String send_listid) {
		this.send_listid = send_listid;
	}
    
}
