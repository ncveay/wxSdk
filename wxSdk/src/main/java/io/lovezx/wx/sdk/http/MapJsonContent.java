package io.lovezx.wx.sdk.http;

import java.util.Map;

import com.alibaba.fastjson.JSON;

public class MapJsonContent extends JSONContent{
    private Map<?, ?> map;
    public MapJsonContent (Map<?, ?> map){
        this.map = map;
    }
    
    @Override
    public String toString(){
        return JSON.toJSONString(map);
    }
}
