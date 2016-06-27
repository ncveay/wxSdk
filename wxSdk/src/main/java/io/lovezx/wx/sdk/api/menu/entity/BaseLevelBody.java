package io.lovezx.wx.sdk.api.menu.entity;

import java.util.ArrayList;
import java.util.List;

public class BaseLevelBody extends NameSupport{
    
    private List<TypeSupport> sub_button = new ArrayList<TypeSupport>();

    public List<TypeSupport> getSub_button() {
        return sub_button;
    }
    public void setSub_button(List<TypeSupport> sub_button) {
        this.sub_button = sub_button;
    }
    @Override
    public boolean checkValid(){
        if(!super.checkValid()) return false;
        if(this.name.length() > 16) return false;
        if(this.sub_button == null) return false;
        if(this.sub_button.size() > 3) return false;
        for(TypeSupport type : sub_button){
            if(!type.checkValid()) return false;
        }
        return true;
    }
}
