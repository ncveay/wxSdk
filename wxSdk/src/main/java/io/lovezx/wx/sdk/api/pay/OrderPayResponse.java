package io.lovezx.wx.sdk.api.pay;

import java.util.Map;

public class OrderPayResponse extends PayReturnResponse{
    /**业务结果code*/
    private String result_code;
    /**交易类型*/
    private String trade_type;
    /**预支付交易标示*/
    private String prepay_id;
    /**二维码链接*/
    private String code_url;
    
    private static final String FLAG_SUCCESS = "SUCCESS";
    
    public void setField(Map<String, String> map){
        super.setField(map);
        result_code = map.get("result_code");
        trade_type = map.get("trade_type");
        prepay_id = map.get("prepay_id");
        code_url = map.get("code_url");
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
            return false;
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
    
    public PayType getPayType(){
        return PayType.valueOf(trade_type.toUpperCase());
    }
    
    public String getPrePayId(){
        return prepay_id;
    }
    
    public String getQRCodeUrl(){
        return code_url;
    }
}
