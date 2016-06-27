package io.lovezx.wx.sdk.api.menu.entity;

import org.apache.commons.lang.StringUtils;

public class KeySupport extends TypeSupport{
    protected String key;

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public boolean checkValid(){
        if(!super.checkValid()) return false;
        return StringUtils.isNotEmpty(key);
    }
}
