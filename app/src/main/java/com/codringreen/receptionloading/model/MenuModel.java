package com.codringreen.receptionloading.model;

import com.codringreen.receptionloading.constants.NavigationType;

public class MenuModel {

    private int menuIcon;
    private String menuName;
    private NavigationType navigationType;


    public int getMenuIcon() {
        return this.menuIcon;
    }

    public void setMenuIcon(int menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public NavigationType getNavigationType() {
        return this.navigationType;
    }

    public void setNavigationType(NavigationType navigationType) {
        this.navigationType = navigationType;
    }

    public MenuModel(int menuIcon, String menuName, NavigationType navigationType) {
        this.menuIcon = menuIcon;
        this.menuName = menuName;
        this.navigationType = navigationType;
    }
}