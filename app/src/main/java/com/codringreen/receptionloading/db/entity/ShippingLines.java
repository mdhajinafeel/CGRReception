package com.codringreen.receptionloading.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "ShippingLines")
public class ShippingLines implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private boolean isSelected;
    private int shippingId;
    private String shippingLine;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

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