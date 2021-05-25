package com.example.vnews.modelJ;

public class MenuRight {
    public String TenMenu;
    public String AnhMenu;

    public MenuRight(String tenMenu, String anhMenu) {
        TenMenu = tenMenu;
        AnhMenu = anhMenu;
    }

    public String getTenMenu() {
        return TenMenu;
    }

    public void setTenMenu(String tenMenu) {
        TenMenu = tenMenu;
    }

    public String getAnhMenu() {
        return AnhMenu;
    }

    public void setAnhMenu(String anhMenu) {
        AnhMenu = anhMenu;
    }
}