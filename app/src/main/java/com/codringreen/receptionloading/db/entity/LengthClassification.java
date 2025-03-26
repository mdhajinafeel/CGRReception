package com.codringreen.receptionloading.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "LengthClassification")
public class LengthClassification implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private boolean isManual;
    private boolean isSelected;
    private String lengthClassification;
    private int lengthClassificationId;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLengthClassificationId() {
        return this.lengthClassificationId;
    }

    public void setLengthClassificationId(int lengthClassificationId) {
        this.lengthClassificationId = lengthClassificationId;
    }

    public String getLengthClassification() {
        return this.lengthClassification;
    }

    public void setLengthClassification(String lengthClassification) {
        this.lengthClassification = lengthClassification;
    }

    public boolean isManual() {
        return this.isManual;
    }

    public void setManual(boolean manual) {
        this.isManual = manual;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }
}