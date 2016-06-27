package io.lovezx.wx.sdk.api.pay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.lovezx.wx.sdk.cache.CacheContants;
import io.lovezx.wx.sdk.context.WxConnectContext;
import io.lovezx.wx.sdk.context.account.WxAccountEntry;
import io.lovezx.wx.sdk.resource.XMapUtil;
import io.lovezx.wx.sdk.security.md5.MD5Util;

public class AbstractPayResponse {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPayResponse.class);
    
    public void setField(Map<String, String> map){
        return_code = map.get("return_code");
        return_msg = map.get("return_msg");
    }
    
    public static boolean checkSign(String response){
        @SuppressWarnings("unchecked")
        Map<String, String> map = Collections.EMPTY_MAP;
        try {
            map = XMapUtil.getMapFromXML(response);
        } catch (Exception e) {
            LOGGER.error("数据解析错误", e);
            return false;
        }
        String signFromAPIResponse = (String)map.get("sign");
        if(signFromAPIResponse == null || "".equals(signFromAPIResponse)){
            return false;
        }
        map.put("sign","");
        String signForAPIResponse = getSign(map, map.get("appid"));
        if(!signForAPIResponse.equals(signFromAPIResponse)){
            return false;
        }
        return true;
    }
    public static String getSign(Map<String,String> map, String appid){
        ArrayList<String> list = new ArrayList<String>();
        for(Map.Entry<String,String> entry:map.entrySet()){
            if(!entry.getValue().equals("")){
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        WxAccountEntry entry = (WxAccountEntry)WxConnectContext.getInstance().getCache().get(CacheContants.ACCOUNT_.name()+appid);
        result += "key=" + entry.getKey();
        result = MD5Util.MD5(result).toUpperCase();
        return result;
    }
    private String return_code;
    
    private String return_msg;

    protected String getReturn_code() {
        return return_code;
    }

    protected String getReturn_msg() {
        return return_msg;
    }

    protected void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    protected void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }
    static final String FLAG_SUCCESS = "SUCCESS";
    /**
     * 检查是否下单失败
     * @return
     */
    public boolean hasError(){
        if(!FLAG_SUCCESS.equals(return_code)){
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
        if(!FLAG_SUCCESS.equals(return_code)){
           return return_msg;
        }else{
            return "";
        }
    }
}
