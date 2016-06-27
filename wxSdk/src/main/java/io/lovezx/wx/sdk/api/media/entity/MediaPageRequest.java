package io.lovezx.wx.sdk.api.media.entity;

import org.apache.commons.lang.StringUtils;

import io.lovezx.wx.sdk.SelfValid;
import io.lovezx.wx.sdk.http.JSONContent;
/**
 * 获取永久素材列表的请求   数量必须在1-20之间
 * @author yuanyi
 *
 */
public class MediaPageRequest extends JSONContent implements SelfValid {
    private String type;
    private int offset;
    private int count;
    
    public boolean checkValid() {
        if(StringUtils.isEmpty(type)) return false;
        try {
            MaterialType.valueOf(type.toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        //只能1-20
        if(count <1 || count >20){
            return false;
        }
        return true;
    }
    
    public String getType() {
        return type;
    }
    public int getOffset() {
        return offset;
    }
    public int getCount() {
        return count;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setOffset(int offset) {
        this.offset = offset;
    }
    public void setCount(int count) {
        this.count = count;
    }
}
