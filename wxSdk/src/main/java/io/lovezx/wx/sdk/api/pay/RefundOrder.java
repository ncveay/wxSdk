package io.lovezx.wx.sdk.api.pay;

import java.util.ArrayList;

import io.lovezx.wx.sdk.Assert;
import io.lovezx.wx.sdk.SelfValid;
import io.lovezx.wx.sdk.api.pay.xml.Node;
import io.lovezx.wx.sdk.api.pay.xml.XmlMap;
import io.lovezx.wx.sdk.http.PostContent;

/**
 * 退款订单
 * @author yuanyi
 *
 */
public class RefundOrder extends AbstractPayRequest implements PostContent, SelfValid{
    /**订单号*/
    private String out_trade_no;
    /**退款单号*/
    private String out_refund_no;
    /**总价格*/
    private String total_fee;
    /**退款金额*/
    private String refund_fee;
    /**操作人员*/
    private String op_user_id;
    
    @Override
    public String toString(){
        XmlMap map = super.getXmlMap();
        map.addNode(new Node("out_trade_no", getOut_trade_no()));
        map.addNode(new Node("out_refund_no", getOut_refund_no()));
        map.addNode(new Node("total_fee", String.valueOf(getTotal_fee())));
        map.addNode(new Node("refund_fee", String.valueOf(getRefund_fee())));
        map.addNode(new Node("op_user_id", getOp_user_id()));
        map.addNode(new Node("sign", getSign(getAppid())));
        return map.toString();
    }
    
    public boolean checkValid() {
        Assert.notNull(getAppid(), "appid 不能为空");
        Assert.notNull(getMch_id(), "商户号不能为空");
        Assert.notNull(getOut_trade_no(), "订单号不能为空");
        Assert.notNull(getOut_trade_no(), "商户订单号不能为空");
        Assert.notNull(getTotal_fee()+"", "总金额不能为空");
        Assert.notNull(getOut_refund_no(), "退单号不能为空");
        Assert.notNull(getOp_user_id(), "操作用户ID不能为空");
        return true;
    }
    
	@Override
	protected ArrayList<String> getSignParams() {
		ArrayList<String> list = new ArrayList<String>();
		list = this.getSignParams(list, "out_trade_no", out_trade_no);
		list = this.getSignParams(list, "out_refund_no", out_refund_no);
		list = this.getSignParams(list, "total_fee", total_fee);
		list = this.getSignParams(list, "refund_fee", refund_fee);
		list = this.getSignParams(list, "op_user_id", op_user_id);
		list = this.getSignParams(list, "out_trade_no", out_trade_no);
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
    
    public String getOut_trade_no() {
        return out_trade_no;
    }
    public String getOut_refund_no() {
        return out_refund_no;
    }
   
    protected String getOp_user_id() {
        return op_user_id;
    }
    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }
    public void setOp_user_id(String op_user_id) {
        this.op_user_id = op_user_id;
    }

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(String refund_fee) {
		this.refund_fee = refund_fee;
	}
}
