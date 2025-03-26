package com.codringreen.receptionloading.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "ContainerNumbers")
public class ContainerNumbers implements Serializable {

    private String containerNumbers;
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContainerNumbers() {
        return this.containerNumbers;
    }

    public void setContainerNumbers(String containerNumbers) {
        this.containerNumbers = containerNumbers;
    }
}