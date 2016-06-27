package io.lovezx.wx.sdk.api.menu.entity;

import java.util.List;

import io.lovezx.wx.sdk.api.AbstractResponse;

public class MenuResponse extends AbstractResponse {
    private Menu menu;
    
    public Menu getMenu() {
        return menu;
    }
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public static class Menu{
        private List<MenuBody> button;

        public List<MenuBody> getButton() {
            return button;
        }
        public void setButton(List<MenuBody> button) {
            this.button = button;
        }
        
    }
}
