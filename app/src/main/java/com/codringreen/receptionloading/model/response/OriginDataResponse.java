package com.codringreen.receptionloading.model.response;

import java.io.Serializable;

public class OriginDataResponse implements Serializable {
    private boolean isSelected;
    private int originId;
    private String originName;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getOriginId() {
        return originId;
    }

    public void setOriginId(int originId) {
        this.originId = originId;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }
}