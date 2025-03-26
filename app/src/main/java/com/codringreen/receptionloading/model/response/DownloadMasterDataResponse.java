package com.codringreen.receptionloading.model.response;

import java.io.Serializable;
import java.util.List;

public class DownloadMasterDataResponse implements Serializable {

    private List<ContainerNumberMasterResponse> containerNumbers;
    private List<ContainerListsDataMasterResponse> containers;
    private List<GirthClassificationMasterResponse> girthClassification;
    private List<LengthClassificationMasterResponse> lengthClassification;
    private List<MeasurementSystemMasterResponse> measurementSystems;
    private List<ProductTypeMasterResponse> productTypes;
    private List<ProductMasterResponse> products;
    private List<PurchaseContractMasterResponse> purchaseContract;
    private List<ReceptionListMasterResponse> receptions;
    private List<ShippingLineMasterResponse> shippingLines;
    private List<SupplierMasterResponse> suppliers;
    private UserDataResponse userData;
    private List<WarehouseMasterResponse> warehouses;

    public List<ContainerNumberMasterResponse> getContainerNumbers() {
        return containerNumbers;
    }

    public void setContainerNumbers(List<ContainerNumberMasterResponse> containerNumbers) {
        this.containerNumbers = containerNumbers;
    }

    public List<ContainerListsDataMasterResponse> getContainers() {
        return containers;
    }

    public void setContainers(List<ContainerListsDataMasterResponse> containers) {
        this.containers = containers;
    }

    public List<GirthClassificationMasterResponse> getGirthClassification() {
        return girthClassification;
    }

    public void setGirthClassification(List<GirthClassificationMasterResponse> girthClassification) {
        this.girthClassification = girthClassification;
    }

    public List<LengthClassificationMasterResponse> getLengthClassification() {
        return lengthClassification;
    }

    public void setLengthClassification(List<LengthClassificationMasterResponse> lengthClassification) {
        this.lengthClassification = lengthClassification;
    }

    public List<MeasurementSystemMasterResponse> getMeasurementSystems() {
        return measurementSystems;
    }

    public void setMeasurementSystems(List<MeasurementSystemMasterResponse> measurementSystems) {
        this.measurementSystems = measurementSystems;
    }

    public List<ProductTypeMasterResponse> getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(List<ProductTypeMasterResponse> productTypes) {
        this.productTypes = productTypes;
    }

    public List<ProductMasterResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductMasterResponse> products) {
        this.products = products;
    }

    public List<PurchaseContractMasterResponse> getPurchaseContract() {
        return purchaseContract;
    }

    public void setPurchaseContract(List<PurchaseContractMasterResponse> purchaseContract) {
        this.purchaseContract = purchaseContract;
    }

    public List<ReceptionListMasterResponse> getReceptions() {
        return receptions;
    }

    public void setReceptions(List<ReceptionListMasterResponse> receptions) {
        this.receptions = receptions;
    }

    public List<ShippingLineMasterResponse> getShippingLines() {
        return shippingLines;
    }

    public void setShippingLines(List<ShippingLineMasterResponse> shippingLines) {
        this.shippingLines = shippingLines;
    }

    public List<SupplierMasterResponse> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierMasterResponse> suppliers) {
        this.suppliers = suppliers;
    }

    public UserDataResponse getUserData() {
        return userData;
    }

    public void setUserData(UserDataResponse userData) {
        this.userData = userData;
    }

    public List<WarehouseMasterResponse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<WarehouseMasterResponse> warehouses) {
        this.warehouses = warehouses;
    }
}