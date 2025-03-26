package com.codringreen.receptionloading.model.response;

import java.io.Serializable;

public class ReceptionDataListMasterResponse implements Serializable {

    private double circumferenceBought;
    private int containerId;
    private String containerNumber;
    private int createdBy;
    private double grossVolume;
    private double lengthBought;
    private double netVolume;
    private int pieces;
    private int receptionDataId;
    private int receptionId;

    public double getCircumferenceBought() {
        return circumferenceBought;
    }

    public void setCircumferenceBought(double circumferenceBought) {
        this.circumferenceBought = circumferenceBought;
    }

    public int getContainerId() {
        return containerId;
    }

    public void setContainerId(int containerId) {
        this.containerId = containerId;
    }

    public String getContainerNumber() {
        return containerNumber;
    }

    public void setContainerNumber(String containerNumber) {
        this.containerNumber = containerNumber;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public double getGrossVolume() {
        return grossVolume;
    }

    public void setGrossVolume(double grossVolume) {
        this.grossVolume = grossVolume;
    }

    public double getLengthBought() {
        return lengthBought;
    }

    public void setLengthBought(double lengthBought) {
        this.lengthBought = lengthBought;
    }

    public double getNetVolume() {
        return netVolume;
    }

    public void setNetVolume(double netVolume) {
        this.netVolume = netVolume;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public int getReceptionDataId() {
        return receptionDataId;
    }

    public void setReceptionDataId(int receptionDataId) {
        this.receptionDataId = receptionDataId;
    }

    public int getReceptionId() {
        return receptionId;
    }

    public void setReceptionId(int receptionId) {
        this.receptionId = receptionId;
    }
}