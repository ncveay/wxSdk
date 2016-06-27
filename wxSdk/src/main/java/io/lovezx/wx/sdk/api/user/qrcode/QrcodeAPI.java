package io.lovezx.wx.sdk.api.user.qrcode;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.lovezx.wx.sdk.Assert;
import io.lovezx.wx.sdk.SdkUtil;
import io.lovezx.wx.sdk.api.APIRequestSupport;
import io.lovezx.wx.sdk.api.RequestParameter;
import io.lovezx.wx.sdk.context.APIContants;

public class QrcodeAPI extends APIRequestSupport {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(QrcodeAPI.class);
	
	/**
	 * 获取二维码URL(永久二维码：字符串场景ID)
	 * @param qrcodeRequest
	 * @return
	 */
	public QrcodeResponse getQrcodeUrl(String scene_str){
		QrcodeRequest qrcodeRequest = new QrcodeRequest(scene_str);
		Assert.isValid(qrcodeRequest);
		return sendRequest(APIContants.QRCODE_CREATE, qrcodeRequest, SdkUtil.createEmptyList(RequestParameter.class), QrcodeResponse.class);
    }
	
	/**
	 * 获取二维码URL(永久二维码：整型场景ID)
	 * @param qrcodeRequest
	 * @return
	 */
	public QrcodeResponse getQrcodeUrl(Long scene_id){
		QrcodeRequest qrcodeRequest = new QrcodeRequest(scene_id);
		Assert.isValid(qrcodeRequest);
		return sendRequest(APIContants.QRCODE_CREATE, qrcodeRequest, SdkUtil.createEmptyList(RequestParameter.class), QrcodeResponse.class);
    }
	
	/**
	 * 获取二维码URL(临时二维码)
	 * @param qrcodeRequest
	 * @return
	 */
	public QrcodeResponse getQrcodeUrl(Integer expire_seconds, Long scene_id){
		QrcodeRequest qrcodeRequest = new QrcodeRequest(expire_seconds, scene_id);
		Assert.isValid(qrcodeRequest);
		return sendRequest(APIContants.QRCODE_CREATE, qrcodeRequest, SdkUtil.createEmptyList(RequestParameter.class), QrcodeResponse.class);
    }
	
	/**
	 * 获取二维码URL
	 * @param qrcodeRequest
	 * @return
	 */
	public QrcodeResponse getQrcodeUrl(QrcodeRequest qrcodeRequest){
		Assert.isValid(qrcodeRequest);
		return sendRequest(APIContants.QRCODE_CREATE, qrcodeRequest, SdkUtil.createEmptyList(RequestParameter.class), QrcodeResponse.class);
    }
	
	/**
	 * 获取二维码图片
	 * @param qrcodeRequest
	 * @return
	 */
	public byte[] getQrcodeImg(String ticket){
		Assert.notNull(ticket, "ticket不能为空！");
		try {
			ticket = URLEncoder.encode(ticket, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("qrcode的ticket转换失败", e);
		}
		List<RequestParameter> params = new ArrayList<RequestParameter>();
		RequestParameter request = new RequestParameter("ticket",ticket);
		params.add(request);
		return downLoad(APIContants.QRCODE_SHOWQRCODE, params);
    }
	

}
