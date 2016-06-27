package io.lovezx.wx.sdk.api.pay;

import java.util.Map;

/**
 * 退款返回数据
 * @author yuanyi
 *
 */
public class QueryOrderResponse extends PayReturnResponse {
    /**业务结果code*/
    private String result_code;
    /**设备号*/
    private String device_info;
    /**用户标识*/
    private String openid;
    /**是否关注公众账号*/
    private String is_subscribe;
    /**交易类型*/
    private String trade_type;
    /**交易状态*/
    private String trade_state;
    /**付款银行*/
    private String bank_type;
    /**总金额*/
    private String total_fee;
    /**货币种类*/
    private String fee_type;
    /**现金支付金额*/
    private String cash_fee;
    /**现金支付货币类型*/
    private String cash_fee_type;
    /**微信支付订单号*/
    private String transaction_id;
    /**商户订单号*/
    private String out_trade_no;
    /**附加数据*/
    private String attach;
    /**支付完成时间*/
    private String time_end;
    /**交易状态描述*/
    private String trade_state_desc;
    
    private static final String FLAG_SUCCESS = "SUCCESS";
    
    
    public String getResult_code() {
		return result_code;
	}
	public String getDevice_info() {
		return device_info;
	}
	public String getOpenid() {
		return openid;
	}
	public String getIs_subscribe() {
		return is_subscribe;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public String getTrade_state() {
		return trade_state;
	}
	public String getBank_type() {
		return bank_type;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public String getFee_type() {
		return fee_type;
	}
	public String getCash_fee() {
		return cash_fee;
	}
	public String getCash_fee_type() {
		return cash_fee_type;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public String getAttach() {
		return attach;
	}
	public String getTime_end() {
		return time_end;
	}
	public String getTrade_state_desc() {
		return trade_state_desc;
	}
	public QueryOrderResponse setResult_code(String result_code) {
		this.result_code = result_code;
		return this;
	}
	public QueryOrderResponse setDevice_info(String device_info) {
		this.device_info = device_info;
		return this;
	}
	public QueryOrderResponse setOpenid(String openid) {
		this.openid = openid;
		return this;
	}
	public QueryOrderResponse setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
		return this;
	}
	public QueryOrderResponse setTrade_type(String trade_type) {
		this.trade_type = trade_type;
		return this;
	}
	public QueryOrderResponse setTrade_state(String trade_state) {
		this.trade_state = trade_state;
		return this;
	}
	public QueryOrderResponse setBank_type(String bank_type) {
		this.bank_type = bank_type;
		return this;
	}
	public QueryOrderResponse setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
		return this;
	}
	public QueryOrderResponse setFee_type(String fee_type) {
		this.fee_type = fee_type;
		return this;
	}
	public QueryOrderResponse setCash_fee(String cash_fee) {
		this.cash_fee = cash_fee;
		return this;
	}
	public QueryOrderResponse setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
		return this;
	}
	public QueryOrderResponse setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
		return this;
	}
	public QueryOrderResponse setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
		return this;
	}
	public QueryOrderResponse setAttach(String attach) {
		this.attach = attach;
		return this;
	}
	public QueryOrderResponse setTime_end(String time_end) {
		this.time_end = time_end;
		return this;
	}
	public QueryOrderResponse setTrade_state_desc(String trade_state_desc) {
		this.trade_state_desc = trade_state_desc;
		return this;
	}
	public void setField(Map<String, String> map){
        super.setField(map);
        result_code = map.get("result_code");
        transaction_id = map.get("transaction_id");
        out_trade_no = map.get("out_trade_no");
        device_info = map.get("device_info");
        openid = map.get("openid");
        is_subscribe = map.get("is_subscribe");
        trade_type = map.get("trade_type");
        trade_state = map.get("trade_state");
        bank_type = map.get("bank_type");
        total_fee = map.get("total_fee");
        fee_type = map.get("fee_type");
        cash_fee = map.get("cash_fee");
        cash_fee_type = map.get("cash_fee_type");
        attach = map.get("attach");
        time_end = map.get("time_end");
        trade_state_desc = map.get("trade_state_desc");
    }
    /**
     * 检查是否失败
     * @return
     */
    public boolean hasError(){
        if(super.hasError()){
            return true;
        }
        if(!FLAG_SUCCESS.equals(result_code)){
            return true;
        }else{
            return true;
        }
    }
    /**
     * 返回错误信息
     * @return
     */
    public String getErrMsg(){
        if(super.hasError()){
            return super.getErrMsg();
        }
        return getErr_code_des();
    }
    
}
