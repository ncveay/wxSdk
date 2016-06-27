package io.lovezx.wx.sdk.api.pay;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.Assert;
import io.lovezx.wx.sdk.SelfValid;
import io.lovezx.wx.sdk.api.pay.xml.Node;
import io.lovezx.wx.sdk.api.pay.xml.XmlMap;
import io.lovezx.wx.sdk.http.JSONContent;
import io.lovezx.wx.sdk.http.PostContent;

public class PayOrder extends AbstractPayRequest implements PostContent, SelfValid{
    
    /**附加数据*/
    private String attach;
    /**商品名称*/
    private String body;
    /**商品详情*/
    private String detail;//no
    /**回调地址*/
    private String notify_url;
    /**支付人*/
    private String openid;
    /**商户订单号*/
    private String out_trade_no;
    /**终端IP*/
    private String spbill_create_ip;
    /**总金额*/
    private String total_fee;
    /**货币类型*/
    private String fee_type;//no
    /**交易类型*/
    private String trade_type;
    /**开始时间*/
    private String time_start;
    /**结束时间*/
    private String time_expire;
    /**商品编号*/
    private String goods_tag;
    /**商品ID*/
    private String product_id;
    /**限制支付类型*/
    private String limit_pay;
    
    public String getAttach() {
        return attach;
    }
    public String getBody() {
        return body;
    }
    public String getDetail() {
        return detail;
    }
    public String getNotify_url() {
        return notify_url;
    }
    public String getOpenid() {
        return openid;
    }
    public String getOut_trade_no() {
        return out_trade_no;
    }
    String getSpbill_create_ip() {
        return spbill_create_ip;
    }
    public String getTotal_fee() {
        return total_fee;
    }
    String getFee_type() {
        return fee_type;
    }
    public String getTrade_type() {
        return trade_type;
    }
    String getTime_start() {
        return time_start;
    }
    public String getTime_expire() {
        return time_expire;
    }
    String getGoods_tag() {
        return goods_tag;
    }
    public String getProduct_id() {
        return product_id;
    }
    String getLimit_pay() {
        return limit_pay;
    }
    public PayOrder setAttach(JSONContent attach) {
        String str = attach.toString();
        if(str.length() > 127){
            throw new IllegalArgumentException("附加数据的长度应小于127");
        }
        this.attach = attach.toString();
        return this;
    }
    public PayOrder setBody(String body) {
        this.body = body;
        return this;
    }
    public PayOrder setDetail(String detail) {
        this.detail = detail;
        return this;
    }
    public PayOrder setNotify_url(String notify_url) {
        this.notify_url = notify_url;
        return this;
    }
    public PayOrder setOpenid(String openid) {
        this.openid = openid;
        return this;
    }
    public PayOrder setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
        return this;
    }
    public PayOrder setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
        return this;
    }
    public PayOrder setTotal_fee(int total_fee) {
        this.total_fee = total_fee+"";
        return this;
    }
    PayOrder setFee_type(String fee_type) {
        this.fee_type = fee_type;
        return this;
    }
    public PayOrder setTrade_type(PayType trade_type) {
        this.trade_type = trade_type.name();
        return this;
    }
    PayOrder setTime_start(String time_start) {
        this.time_start = time_start;
        return this;
    }
    public PayOrder setTime_expire(String time_expire) {
        this.time_expire = time_expire;
        return this;
    }
    PayOrder setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
        return this;
    }
    public PayOrder setProduct_id(String product_id) {
        this.product_id = product_id;
        return this;
    }
    PayOrder setLimit_pay(String limit_pay) {
        this.limit_pay = limit_pay;
        return this;
    }
    
    @Override
    public String toString(){
        XmlMap map = super.getXmlMap();
        map = this.addNode(map, "attach", attach);
        map = this.addNode(map, "body", body);
        map = this.addNode(map, "detail", detail);
        map = this.addNode(map, "notify_url", notify_url);
        map = this.addNode(map, "out_trade_no", out_trade_no);
        map = this.addNode(map, "spbill_create_ip", spbill_create_ip);
        map = this.addNode(map, "total_fee", total_fee);
        map = this.addNode(map, "fee_type", fee_type);
        map = this.addNode(map, "trade_type", trade_type);
        map = this.addNode(map, "time_start", time_start);
        map = this.addNode(map, "time_expire", time_expire);
        map = this.addNode(map, "goods_tag", goods_tag);
        map = this.addNode(map, "product_id", product_id);
        map = this.addNode(map, "limit_pay", limit_pay);
        if(PayType.valueOf(getTrade_type()) == PayType.JSAPI){
        	map = this.addNode(map, "openid", openid);
        }
        map.addNode(new Node("sign", getSign(getAppid())));
        return map.toString();
    }
    
    private XmlMap addNode(XmlMap map, String paramName, String param){
    	if(null != param && !"".equals(param)){
    		map.addNode(new Node(paramName, param));
		}
		return map;
    }
    
    public boolean checkValid() {
        Assert.notNull(getAppid(), "appid 不能为空");
        Assert.notNull(getMch_id(), "商户号不能为空");
        Assert.notNull(getBody(), "商品名称不能为空");
        Assert.notNull(getOut_trade_no(), "商户订单号不能为空");
        Assert.notNull(getTotal_fee(), "总金额不能为空");
        Assert.notNull(getSpbill_create_ip(), "终端IP不能为空");
        Assert.notNull(getNotify_url(), "回调URL不能为空");
        Assert.notNull(getTrade_type(), "支付类型不能为空！");
        if(StringUtils.equals(getTrade_type(), PayType.JSAPI.name())){
            Assert.notNull(getOpenid(), "jsAPI call , openid can not be null");
        }
        return true;
    }
    
	@Override
	protected ArrayList<String> getSignParams() {
		ArrayList<String> list = new ArrayList<String>();
		list = this.getSignParams(list, "attach", attach);
		list = this.getSignParams(list, "body", body);
		list = this.getSignParams(list, "detail", detail);
		list = this.getSignParams(list, "notify_url", notify_url);
		list = this.getSignParams(list, "openid", openid);
		list = this.getSignParams(list, "out_trade_no", out_trade_no);
		list = this.getSignParams(list, "spbill_create_ip", spbill_create_ip);
		list = this.getSignParams(list, "total_fee", total_fee);
		list = this.getSignParams(list, "fee_type", fee_type);
		list = this.getSignParams(list, "trade_type", trade_type);
		list = this.getSignParams(list, "time_start", time_start);
		list = this.getSignParams(list, "time_expire", time_expire);
		list = this.getSignParams(list, "goods_tag", goods_tag);
		list = this.getSignParams(list, "product_id", product_id);
		list = this.getSignParams(list, "limit_pay", limit_pay);
		list = this.getSignParams(list, "appid", this.getAppid());
		list = this.getSignParams(list, "mch_id", this.getMch_id());
		list = this.getSignParams(list, "nonce_str", this.getNonce_str());
		return list;
	}
	
	private ArrayList<String> getSignParams(ArrayList<String> list, String paramName, String param){
		if(null != param && !"".equals(param)){
			list.add(paramName + "=" + param);
		}
		return list;
	}
	
}
