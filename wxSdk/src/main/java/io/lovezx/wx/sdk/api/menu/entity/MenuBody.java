package io.lovezx.wx.sdk.api.menu.entity;

import java.util.ArrayList;
import java.util.List;

import io.lovezx.wx.sdk.SelfValid;
import io.lovezx.wx.sdk.http.JSONContent;

public class MenuBody extends JSONContent implements SelfValid {
private List<NameSupport> button = new ArrayList<NameSupport>();
    
    public List<NameSupport> getButton() {
        return button;
    }
    public void setButton(List<NameSupport> button) {
        this.button = button;
    }

    public boolean checkValid() {
        if(button == null) return false;
        if(button.isEmpty()) return false;
        for(NameSupport nameSupport : button){
            if(!nameSupport.checkValid()) return false;
        }
        return true;
    }

}
