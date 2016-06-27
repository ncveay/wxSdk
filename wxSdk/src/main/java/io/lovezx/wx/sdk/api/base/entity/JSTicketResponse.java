package io.lovezx.wx.sdk.api.base.entity;

import io.lovezx.wx.sdk.api.AbstractResponse;

public class JSTicketResponse extends AbstractResponse {

	private String ticket;
	private int expires_in;
	public String getTicket() {
		return ticket;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	
	
}
