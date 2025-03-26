package com.codringreen.receptionloading.model.response;

import java.io.Serializable;

public class LengthClassificationMasterResponse implements Serializable {

    private boolean isManual;
    private String lengthClassification;
    private int lengthClassificationId;

    public boolean isManual() {
        return isManual;
    }

    public void setManual(boolean manual) {
        isManual = manual;
    }

    public String getLengthClassification() {
        return lengthClassification;
    }

    public void setLengthClassification(String lengthClassification) {
        this.lengthClassification = lengthClassification;
    }

    public int getLengthClassificationId() {
        return lengthClassificationId;
    }

    public void setLengthClassificationId(int lengthClassificationId) {
        this.lengthClassificationId = lengthClassificationId;
    }
}