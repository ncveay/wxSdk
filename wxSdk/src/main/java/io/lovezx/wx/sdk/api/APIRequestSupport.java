package io.lovezx.wx.sdk.api;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import io.lovezx.wx.sdk.Assert;
import io.lovezx.wx.sdk.aop.requestInterceptor.Intercept;
import io.lovezx.wx.sdk.api.pay.AbstractPayResponse;
import io.lovezx.wx.sdk.context.APIContants;
import io.lovezx.wx.sdk.context.WxConnectContext;
import io.lovezx.wx.sdk.http.PostContent;
import io.lovezx.wx.sdk.resource.XMapUtil;

public class APIRequestSupport {
    
    private String appid;
    public APIRequestSupport SetAppid(String appid){
        this.appid = appid;
        return this;
    }
    public String getAppid() {
    	Assert.notNull(appid, "appid为空，请检查参数");
        return appid;
    }


    private static Logger LOGGER = LoggerFactory.getLogger(APIRequestSupport.class);

    private String buildURL(APIContants api, List<RequestParameter> params){
        WxConnectContext context = WxConnectContext.getInstance();
        String url = context.getApiContant(api);
        if(StringUtils.isEmpty(url)){
            throw new IllegalArgumentException("请检查配置文件是否正确， url不能为空！");
        }
        url += "?access_token="+context.getAccessToken(appid);
        StringBuilder urlBuilder = new StringBuilder(url);
        if(params!=null && !params.isEmpty()){
            for(RequestParameter param : params){
                urlBuilder.append("&").append(param.getKey()).append("=").append(param.getValue());
            }
        }
        return urlBuilder.toString();
    }
    
    private String buildURLNoAccessToken(APIContants api, List<RequestParameter> params){
        WxConnectContext context = WxConnectContext.getInstance();
        String url = context.getApiContant(api);
        if(StringUtils.isEmpty(url)){
            throw new IllegalArgumentException("请检查配置文件是否正确， url不能为空！");
        }
        url += "?_v=1";
        StringBuilder urlBuilder = new StringBuilder(url);
        if(params!=null && !params.isEmpty()){
            for(RequestParameter param : params){
                urlBuilder.append("&").append(param.getKey()).append("=").append(param.getValue());
            }
        }
        return urlBuilder.toString();
    }
    
    private void checkResponse(String response){
        if(StringUtils.isEmpty(response)){
            LOGGER.error("获取不到微信端信息，网络连接原因？或是微信服务出错？");
            throw new IllegalStateException("获取不到微信端信息，网络连接原因？或是微信服务出错？");
        }
    }
    
    @Intercept
    protected byte[] downLoad(APIContants api, List<RequestParameter> params){
        WxConnectContext context = WxConnectContext.getInstance();
        String url = buildURL(api, params);
        LOGGER.info("send request :"+url);
        return context.getCommonHttpServer().downLoad(url);
    }
    
    @Intercept
    protected byte[] downLoadByPost(APIContants api, PostContent content){
        WxConnectContext context = WxConnectContext.getInstance();
        String url = buildURL(api, null);
        LOGGER.info("send request :"+url);
        return context.getCommonHttpServer().downLoadByPost(url, content);
    }
    
    /**
     * post请求用  返回对象
     * @param api
     * @param content
     * @param expectClass
     * @return
     */
    protected <T extends AbstractResponse> T sendRequest(APIContants api, PostContent content, List<RequestParameter> params, Class<T> expectClass){
        return JSON.parseObject(sendRequest_0(api, content, params).toJSONString(), expectClass);
    }
    
    /**
     * post请求用 返回json
     * @param api
     * @param content
     * @return
     */
    protected JSONObject sendRequest(APIContants api, PostContent content, List<RequestParameter> params){
        return sendRequest_0(api, content, params);
    }
    
    @Intercept
    protected <T extends AbstractPayResponse> T sendPayRequest(APIContants api, PostContent content, List<RequestParameter> params, Class<T> target){
        WxConnectContext context = WxConnectContext.getInstance();
        String url = buildURLNoAccessToken(api, params);
        LOGGER.debug("send request :"+url);
        String response = context.getPayHttpServer(appid).sendPost(url, content);
        LOGGER.debug("response :"+response);
        checkResponse(response);
        try {
            Map<String, String> map = XMapUtil.getMapFromXML(response);
            Field[] fields=target.getDeclaredFields();
            boolean checkSign=false;
            if(fields!=null&&fields.length>0){
            	for (Field field : fields) {
            		String varName = field.getName();
            		if("sign".equals(varName)){
            			checkSign=true;
            			break;
            		}
				}
            }
            if(checkSign&&!AbstractPayResponse.checkSign(response)){
                LOGGER.debug("微信端返回数据校验失败 ");
                map.put("result_code", "FAIL");
                map.put("err_code_des", "微信端返回数据校验失败");
            }
            try {
                T t = target.newInstance();
                t.setField(map);
                return t;
            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalStateException("data set error", e);
            } 
           
        } catch (ParserConfigurationException e) {
            LOGGER.error("response data parse error!", e);
            throw new IllegalArgumentException("response data parse error!", e);
        } catch (IOException e) {
            LOGGER.error("read response data error", e);
            throw new IllegalArgumentException("read response data error", e);
        } catch (SAXException e) {
            LOGGER.error("response data parse error!", e);
            throw new IllegalArgumentException("response data parse error!", e);
        }
    }
    
    @Intercept
    protected JSONObject sendRequest_0(APIContants api, PostContent content, List<RequestParameter> params){
        WxConnectContext context = WxConnectContext.getInstance();
        String url = buildURL(api, params);
        LOGGER.debug("send request :"+url);
        String response = null;
        if(content == null){
            //没有请求体 get请求
            response = context.getCommonHttpServer().sendGet(url,1);
        }else{
            //有请求体，post请求
            response = context.getCommonHttpServer().sendPost(url, content);
        }
        LOGGER.debug("response :"+response);
        checkResponse(response);
        return JSON.parseObject(response);
    }
    
    /**
     * post请求用  form表单post
     * @param api
     * @param content
     * @param expectClass
     * @return
     */
    @Intercept
    protected <T extends AbstractResponse> T sendRequestFormData(APIContants api, byte[] content, String fileName, List<RequestParameter> params, Class<T> expectClass){
        WxConnectContext context = WxConnectContext.getInstance();
        String url = buildURL(api, params);
        String response = context.getCommonHttpServer().postFile(url, content, fileName);
        checkResponse(response);
        return JSON.parseObject(response, expectClass);
    }
    /**
     * 仅用于视频上传，上传其他 请使用{@link #sendRequestFormData}
     * @param api
     * @param file
     * @param content
     * @param expectClass
     * @return
     */
    @Intercept
    protected <T extends AbstractResponse> T sendVideoFile(APIContants api, byte[] content, String fileName, PostContent post, Class<T> expectClass){
        WxConnectContext context = WxConnectContext.getInstance();
        String url = buildURL(api, null);
        String response = context.getCommonHttpServer().postFile(url, content, fileName, post);
        checkResponse(response);
        return JSON.parseObject(response, expectClass);
    }
    
    /**
     * get请求用, 用<code>sendRequest(APIContants api, JSONContent content, Class<T> expectClass)<code>实现
     * @param api
     * @param expectClass
     * @return
     */
    protected <T extends AbstractResponse> T sendRequest(APIContants api, List<RequestParameter> params, Class<T> expectClass){
        return sendRequest(api, null, params, expectClass);
    }
    /**
     * 返回JSON的get请求
     * @param api
     * @param params
     * @return
     */
    protected JSONObject sendRequest(APIContants api, List<RequestParameter> params){
        return sendRequest_0(api, null, params);
    }
}
