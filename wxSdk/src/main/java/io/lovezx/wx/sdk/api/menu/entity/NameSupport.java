package io.lovezx.wx.sdk.api.menu.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class NameSupport {
    protected String name;
    
    /**
     * 目前微信API不支持第三级菜单，所以不支持修改
     */
    protected List<TypeSupport> sub_button = Collections.unmodifiableList(new ArrayList<TypeSupport>());

    public List<TypeSupport> getSub_button() {
        return sub_button;
    }
    public void setSub_button(List<TypeSupport> sub_button) {
        this.sub_button = sub_button;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean checkValid(){
    	if(StringUtils.isEmpty(name)) return false;
        for(TypeSupport typeSupport : sub_button){
            if(!typeSupport.checkValid()) return false;
        }
        return true;
    }
}
