package com.codringreen.receptionloading.model.response;

import java.io.Serializable;
import java.util.List;

public class ReceptionListMasterResponse implements Serializable {

    private int closedBy;
    private long closedDate;
    private int contractId;
    private int createdBy;
    private long createdDate;
    private String inventoryOrder;
    private boolean isClosed;
    private boolean isCreateFarm;
    private boolean isSpecialUploaded;
    private double logisticCost;
    private int logisticPayTo;
    private int measurementSystemId;
    private int originId;
    private int productId;
    private int productTypeId;
    private String receivedDate;
    private List<ReceptionContainerMappingResponse> receptionContainerMapping;
    private List<ReceptionDataListMasterResponse> receptionData;
    private int receptionId;
    private int supplierId;
    private int supplierProductId;
    private int supplierProductTypeId;
    private double totalGrossVolume;
    private double totalNetVolume;
    private int totalPieces;
    private String truckPlateNumber;
    private int warehouseId;

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

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
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

    public String getInventoryOrder() {
        return inventoryOrder;
    }

    public void setInventoryOrder(String inventoryOrder) {
        this.inventoryOrder = inventoryOrder;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public boolean isCreateFarm() {
        return isCreateFarm;
    }

    public void setCreateFarm(boolean createFarm) {
        isCreateFarm = createFarm;
    }

    public boolean isSpecialUploaded() {
        return isSpecialUploaded;
    }

    public void setSpecialUploaded(boolean specialUploaded) {
        isSpecialUploaded = specialUploaded;
    }

    public double getLogisticCost() {
        return logisticCost;
    }

    public void setLogisticCost(double logisticCost) {
        this.logisticCost = logisticCost;
    }

    public int getLogisticPayTo() {
        return logisticPayTo;
    }

    public void setLogisticPayTo(int logisticPayTo) {
        this.logisticPayTo = logisticPayTo;
    }

    public int getMeasurementSystemId() {
        return measurementSystemId;
    }

    public void setMeasurementSystemId(int measurementSystemId) {
        this.measurementSystemId = measurementSystemId;
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

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public List<ReceptionContainerMappingResponse> getReceptionContainerMapping() {
        return receptionContainerMapping;
    }

    public void setReceptionContainerMapping(List<ReceptionContainerMappingResponse> receptionContainerMapping) {
        this.receptionContainerMapping = receptionContainerMapping;
    }

    public List<ReceptionDataListMasterResponse> getReceptionData() {
        return receptionData;
    }

    public void setReceptionData(List<ReceptionDataListMasterResponse> receptionData) {
        this.receptionData = receptionData;
    }

    public int getReceptionId() {
        return receptionId;
    }

    public void setReceptionId(int receptionId) {
        this.receptionId = receptionId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getSupplierProductId() {
        return supplierProductId;
    }

    public void setSupplierProductId(int supplierProductId) {
        this.supplierProductId = supplierProductId;
    }

    public int getSupplierProductTypeId() {
        return supplierProductTypeId;
    }

    public void setSupplierProductTypeId(int supplierProductTypeId) {
        this.supplierProductTypeId = supplierProductTypeId;
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

    public String getTruckPlateNumber() {
        return truckPlateNumber;
    }

    public void setTruckPlateNumber(String truckPlateNumber) {
        this.truckPlateNumber = truckPlateNumber;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }
}