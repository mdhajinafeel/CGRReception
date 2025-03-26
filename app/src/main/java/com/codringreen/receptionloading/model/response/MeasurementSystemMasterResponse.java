package com.codringreen.receptionloading.model.response;

import java.io.Serializable;

public class MeasurementSystemMasterResponse implements Serializable {

    private int measurementSystemId;
    private String measurementSystemName;
    private int productTypeId;

    public int getMeasurementSystemId() {
        return measurementSystemId;
    }

    public void setMeasurementSystemId(int measurementSystemId) {
        this.measurementSystemId = measurementSystemId;
    }

    public String getMeasurementSystemName() {
        return measurementSystemName;
    }

    public void setMeasurementSystemName(String measurementSystemName) {
        this.measurementSystemName = measurementSystemName;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }
}