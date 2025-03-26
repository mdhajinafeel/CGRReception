package com.codringreen.receptionloading.model.response;

import java.io.Serializable;

public class ContainerListsDataMasterResponse implements Serializable {

    private int category;
    private int closedBy;
    private long closedDate;
    private int containerId;
    private String containerNumber;
    private int createdBy;
    private long createdDate;
    private String dispatchDate;
    private String existingContainerNumber;
    private boolean isClosed;
    private boolean isContainerAvailable;
    private boolean isSpecialUploaded;
    private int originId;
    private int productId;
    private int productTypeId;
    private String sealNumber;
    private int shippingLine;
    private double totalGrossVolume;
    private double totalNetVolume;
    private int totalPieces;
    private int warehouseId;

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(int closedBy) {
        this.closedBy = closedBy;
    }

    public long getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(long closedDate) {
        this.closedDate = closedDate;
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

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public String getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(String dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public String getExistingContainerNumber() {
        return existingContainerNumber;
    }

    public void setExistingContainerNumber(String existingContainerNumber) {
        this.existingContainerNumber = existingContainerNumber;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public boolean isContainerAvailable() {
        return isContainerAvailable;
    }

    public void setContainerAvailable(boolean containerAvailable) {
        isContainerAvailable = containerAvailable;
    }

    public boolean isSpecialUploaded() {
        return isSpecialUploaded;
    }

    public void setSpecialUploaded(boolean specialUploaded) {
        isSpecialUploaded = specialUploaded;
    }

    public int getOriginId() {
        return originId;
    }

    public void setOriginId(int originId) {
        this.originId = originId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getSealNumber() {
        return sealNumber;
    }

    public void setSealNumber(String sealNumber) {
        this.sealNumber = sealNumber;
    }

    public int getShippingLine() {
        return shippingLine;
    }

    public void setShippingLine(int shippingLine) {
        this.shippingLine = shippingLine;
    }

    public double getTotalGrossVolume() {
        return totalGrossVolume;
    }

    public void setTotalGrossVolume(double totalGrossVolume) {
        this.totalGrossVolume = totalGrossVolume;
    }

    public double getTotalNetVolume() {
        return totalNetVolume;
    }

    public void setTotalNetVolume(double totalNetVolume) {
        this.totalNetVolume = totalNetVolume;
    }

    public int getTotalPieces() {
        return totalPieces;
    }

    public void setTotalPieces(int totalPieces) {
        this.totalPieces = totalPieces;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }
}