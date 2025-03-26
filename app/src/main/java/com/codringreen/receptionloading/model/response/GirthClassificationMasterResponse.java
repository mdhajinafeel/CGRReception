package com.codringreen.receptionloading.model.response;

import java.io.Serializable;

public class GirthClassificationMasterResponse implements Serializable {

    private String girthClassification;
    private int girthClassificationId;
    private boolean isManual;

    public String getGirthClassification() {
        return girthClassification;
    }

    public void setGirthClassification(String girthClassification) {
        this.girthClassification = girthClassification;
    }

    public int getGirthClassificationId() {
        return girthClassificationId;
    }

    public void setGirthClassificationId(int girthClassificationId) {
        this.girthClassificationId = girthClassificationId;
    }

    public boolean isManual() {
        return isManual;
    }

    public void setManual(boolean manual) {
        isManual = manual;
    }
}