package io.lovezx.wx.sdk.api.pay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import io.lovezx.wx.sdk.api.pay.xml.Node;
import io.lovezx.wx.sdk.api.pay.xml.XmlMap;
import io.lovezx.wx.sdk.cache.CacheContants;
import io.lovezx.wx.sdk.context.WxConnectContext;
import io.lovezx.wx.sdk.context.account.WxAccountEntry;
import io.lovezx.wx.sdk.security.md5.MD5Util;

public abstract class AbstractRequest {
    
    private String mch_id;
    
    private String nonce_str = getRandomStringByLength(10);
    
    protected XmlMap getXmlMap(){
        XmlMap map = new XmlMap();
        map.addNode(new Node("mch_id", mch_id));
        map.addNode(new Node("nonce_str", nonce_str));
        return map;
    }
    
    protected abstract ArrayList<String> getSignParams();
    
    /**
     * 签名算法
     * @param o 要参与签名的数据对象
     * @return 签名
     * @throws IllegalAccessException
     */
    public String getSign(String appid) {
        ArrayList<String> list = this.getSignParams();
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]).append("&");
        }
        String result = sb.toString();
        WxAccountEntry entry = (WxAccountEntry)WxConnectContext.getInstance().getCache().get(CacheContants.ACCOUNT_.name()+appid);
        result = result + "key=" + entry.getKey();
        result = MD5Util.MD5(result).toUpperCase();
        return result;
    }
    
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    protected String getMch_id() {
        return mch_id;
    }

    protected String getNonce_str() {
        return nonce_str;
    }

    protected void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    protected void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }
}
