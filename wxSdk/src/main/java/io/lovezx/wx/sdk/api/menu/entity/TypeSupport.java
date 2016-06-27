package io.lovezx.wx.sdk.api.menu.entity;

import org.apache.commons.lang.StringUtils;

public class TypeSupport extends NameSupport{
    protected String type;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    @Override
    public boolean checkValid(){
        if(!super.checkValid()) return false;
        return StringUtils.isNotEmpty(type);
    }
}
