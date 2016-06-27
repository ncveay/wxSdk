package io.lovezx.wx.sdk.http;

import com.alibaba.fastjson.JSON;
/**
 * 实现此接口必须提供setter/getter方法
 * @author yuanyi
 *
 */
public class JSONContent implements PostContent{

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
    
}
