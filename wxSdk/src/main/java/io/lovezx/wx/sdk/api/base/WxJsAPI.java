package io.lovezx.wx.sdk.api.base;

import java.util.ArrayList;
import java.util.List;

import io.lovezx.wx.sdk.api.APIRequestSupport;
import io.lovezx.wx.sdk.api.RequestParameter;
import io.lovezx.wx.sdk.api.base.entity.JSTicketResponse;
import io.lovezx.wx.sdk.context.APIContants;

public class WxJsAPI  extends APIRequestSupport{

	
	public JSTicketResponse getJSTicket(){
		List<RequestParameter> params = new ArrayList<RequestParameter>();
		params.add(new RequestParameter("type", "jsapi"));
		return sendRequest(APIContants.JS_API_TICKET, params, JSTicketResponse.class);
	} 
	
}
