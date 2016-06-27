package io.lovezx.wx.sdk.api.pay;

import java.util.Map;

/**
 * 退款返回数据
 * @author yuanyi
 *
 */
public class RefundPayResponse extends PayReturnResponse {
    /**业务结果code*/
    private String result_code;
    /**微信订单号*/
    private String transaction_id;
    /**商户订单号*/
    private String out_trade_no;
    /**商户退单号*/
    private String out_refund_no;
    /**微信退款单号*/
    private String refund_id;
    /**退款金额*/
    private String refund_fee;
    /**订单总金额*/
    private String total_fee;
    /**现金支付金额*/
    private String cash_fee;
    
    protected String getTransaction_id() {
        return transaction_id;
    }
    protected String getOut_trade_no() {
        return out_trade_no;
    }
    protected String getOut_refund_no() {
        return out_refund_no;
    }
    protected String getRefund_id() {
        return refund_id;
    }
    protected String getRefund_fee() {
        return refund_fee;
    }
    protected String getTotal_fee() {
        return total_fee;
    }
    protected String getCash_fee() {
        return cash_fee;
    }
    private static final String FLAG_SUCCESS = "SUCCESS";
    
    public void setField(Map<String, String> map){
        super.setField(map);
        result_code = map.get("result_code");
        transaction_id = map.get("transaction_id");
        out_trade_no = map.get("out_trade_no");
        out_refund_no = map.get("out_refund_no");
        refund_id = map.get("refund_id");
        refund_fee = map.get("refund_fee");
        total_fee = map.get("total_fee");
        cash_fee = map.get("cash_fee");
    }
    /**
     * 检查是否下单失败
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
