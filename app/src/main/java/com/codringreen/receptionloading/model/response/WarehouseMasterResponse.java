package com.codringreen.receptionloading.model.response;

import java.io.Serializable;

public class WarehouseMasterResponse implements Serializable {

    private int pol;
    private int warehouseId;
    private String warehouseName;

    public int getPol() {
        return pol;
    }

    public void setPol(int pol) {
        this.pol = pol;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
}