package io.lovezx.wx.sdk.api.pay.redPackage;

import java.util.ArrayList;

import io.lovezx.wx.sdk.Assert;
import io.lovezx.wx.sdk.SelfValid;
import io.lovezx.wx.sdk.ValidateException;
import io.lovezx.wx.sdk.api.pay.xml.Node;
import io.lovezx.wx.sdk.api.pay.xml.XmlMap;
import io.lovezx.wx.sdk.http.PostContent;

/**
 * 微信红包
 * 
 * @author tantx
 * 
 */
public class RedPacket extends AbstractRedPacketRequest implements PostContent, SelfValid{
	/** 商户订单号 是 28 **/
	private String mch_billno;
	/** 商户名称 是 32 **/
	private String send_name;
	/** 用户openid 是 32 **/
	private String re_openid;
	/** 付款金额，单位分 是 **/
	private int total_amount;
	/** 红包发放总人数 是 **/
	private int total_num;
	/** 红包祝福语 是 128 **/
	private String wishing;
	/** Ip地址 是 15 **/
	private String client_ip;
	/** 活动名称 是 32 **/
	private String act_name;
	/** 备注 是 256 **/
	private String remark;
	
	
	@Override
    public String toString(){
        XmlMap map = super.getXmlMap();
        map.addNode(new Node("mch_billno", getMch_billno()));
        map.addNode(new Node("send_name", getSend_name()));
        map.addNode(new Node("re_openid", getRe_openid()));
        map.addNode(new Node("total_amount", String.valueOf(getTotal_amount())));
        map.addNode(new Node("total_num", String.valueOf(getTotal_num())));
        map.addNode(new Node("wishing", getWishing()));
        map.addNode(new Node("client_ip", getClient_ip()));
        map.addNode(new Node("act_name", getAct_name()));
        map.addNode(new Node("remark", getRemark()));
        map.addNode(new Node("sign", getSign(getWxappid())));
        return map.toString();
    }
    
    public boolean checkValid() {
        Assert.notNull(getWxappid(), "wxappid 不能为空");
        Assert.notNull(getMch_id(), "商户号不能为空");
        Assert.notNull(getMch_billno(), "商户订单号不能为空");
        Assert.notNull(getSend_name(), "商户名称不能为空");
        Assert.notNull(getRe_openid(), "用户openid不能为空");
        if(getTotal_amount()<=0){
            throw new ValidateException("付款金额不能少于或等于0");
        }
        if(getTotal_num()<1){
        	throw new ValidateException("红包发放总人不能少于1");
        }
        Assert.notNull(getWishing(), "红包祝福语不能为空");
        Assert.notNull(getClient_ip(), "Ip地址不能为空");
        Assert.notNull(getRemark(), "备注不能为空");
        return true;
    }
    
	@Override
	protected ArrayList<String> getSignParams() {
		ArrayList<String> list = new ArrayList<String>();
		list = this.getSignParams(list, "mch_billno", getMch_billno());
		list = this.getSignParams(list, "send_name", getSend_name());
		list = this.getSignParams(list, "re_openid", getRe_openid());
		list = this.getSignParams(list, "total_amount", String.valueOf(getTotal_amount()));
		list = this.getSignParams(list, "total_num", String.valueOf(getTotal_num()));
		list = this.getSignParams(list, "wishing", getWishing());
		list = this.getSignParams(list, "act_name", getAct_name());
		list = this.getSignParams(list, "client_ip", getClient_ip());
		list = this.getSignParams(list, "remark", getRemark());
		list = this.getSignParams(list, "wxappid", this.getWxappid());
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

	public String getMch_billno() {
		return mch_billno;
	}

	public void setMch_billno(String mch_billno) {
		this.mch_billno = mch_billno;
	}

	public String getSend_name() {
		return send_name;
	}

	public void setSend_name(String send_name) {
		this.send_name = send_name;
	}

	public String getRe_openid() {
		return re_openid;
	}

	public void setRe_openid(String re_openid) {
		this.re_openid = re_openid;
	}

	public int getTotal_amount() {
		return total_amount;
	}

	/**
	 * 付款金额，单位分
	 * @param total_amount
	 */
	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}

	public int getTotal_num() {
		return total_num;
	}

	public void setTotal_num(int total_num) {
		this.total_num = total_num;
	}

	public String getWishing() {
		return wishing;
	}

	public void setWishing(String wishing) {
		this.wishing = wishing;
	}

	public String getClient_ip() {
		return client_ip;
	}

	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}

	public String getAct_name() {
		return act_name;
	}

	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
