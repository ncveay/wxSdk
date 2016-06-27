package io.lovezx.wx.sdk.api.pay;

import java.util.ArrayList;

import io.lovezx.wx.sdk.Assert;
import io.lovezx.wx.sdk.SelfValid;
import io.lovezx.wx.sdk.api.pay.xml.Node;
import io.lovezx.wx.sdk.api.pay.xml.XmlMap;
import io.lovezx.wx.sdk.http.PostContent;

/**
 * 查询订单
 * @author yuanyi
 *
 */
public class QueryOrder extends AbstractPayRequest implements PostContent, SelfValid{
    /**订单号*/
    private String out_trade_no;
    
    @Override
    public String toString(){
        XmlMap map = super.getXmlMap();
        map.addNode(new Node("out_trade_no", getOut_trade_no()));
        map.addNode(new Node("sign", getSign(getAppid())));
        return map.toString();
    }
    
    public boolean checkValid() {
        Assert.notNull(getAppid(), "appid 不能为空");
        Assert.notNull(getMch_id(), "商户号不能为空");
        Assert.notNull(getOut_trade_no(), "订单号不能为空");
        Assert.notNull(getOut_trade_no(), "商户订单号不能为空");
        return true;
    }
    
    public String getOut_trade_no() {
        return out_trade_no;
    }

	@Override
	protected ArrayList<String> getSignParams() {
		ArrayList<String> list = new ArrayList<String>();
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
    
}
