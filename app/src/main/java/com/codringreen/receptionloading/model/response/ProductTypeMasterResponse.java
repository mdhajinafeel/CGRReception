package com.codringreen.receptionloading.model.response;

import java.io.Serializable;

public class ProductTypeMasterResponse implements Serializable {

    private int productTypeId;
    private String productTypeName;

    public int getProductTypeId() {
        return this.productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductTypeName() {
        return this.productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }
}