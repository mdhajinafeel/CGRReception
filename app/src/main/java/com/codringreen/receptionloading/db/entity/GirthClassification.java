package com.codringreen.receptionloading.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "GirthClassification")
public class GirthClassification implements Serializable {

    private String girthClassification;
    private int girthClassificationId;
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private boolean isManual;
    private boolean isSelected;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGirthClassificationId() {
        return this.girthClassificationId;
    }

    public void setGirthClassificationId(int girthClassificationId) {
        this.girthClassificationId = girthClassificationId;
    }

    public String getGirthClassification() {
        return this.girthClassification;
    }

    public void setGirthClassification(String girthClassification) {
        this.girthClassification = girthClassification;
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