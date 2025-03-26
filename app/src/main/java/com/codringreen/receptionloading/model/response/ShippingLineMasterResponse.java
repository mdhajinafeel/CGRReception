package com.codringreen.receptionloading.model.response;

import java.io.Serializable;

public class ShippingLineMasterResponse implements Serializable {

    private int shippingId;
    private String shippingLine;

    public int getShippingId() {
        return shippingId;
    }

    public void setShippingId(int shippingId) {
        this.shippingId = shippingId;
    }

    public String getShippingLine() {
        return shippingLine;
    }

    public void setShippingLine(String shippingLine) {
        this.shippingLine = shippingLine;
    }
}