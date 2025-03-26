package com.codringreen.receptionloading.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.codringreen.receptionloading.db.entity.FarmDataEntry;
import com.codringreen.receptionloading.db.entity.FarmDetails;
import com.codringreen.receptionloading.db.entity.MeasurementSystems;
import com.codringreen.receptionloading.db.entity.PurchaseContract;
import com.codringreen.receptionloading.db.entity.SupplierProductTypes;
import com.codringreen.receptionloading.db.entity.SupplierProducts;
import com.codringreen.receptionloading.db.entity.Suppliers;
import com.codringreen.receptionloading.db.entity.Warehouses;
import com.codringreen.receptionloading.repository.FarmRepository;
import com.codringreen.receptionloading.repository.MasterRepository;

import java.util.List;

import javax.inject.Inject;

public class FarmViewModel extends BaseViewModel {

    @SuppressLint("StaticFieldLeak")
    private Context context;
    private final MasterRepository masterRepository;
    private final FarmRepository farmRepository;
    public Suppliers selectedSupplier;
    public Warehouses selectedWarehouse;
    public SupplierProductTypes selectedSuppliersProductTypes;
    public SupplierProducts selectedSuppliersProducts;
    public MeasurementSystems selectedMeasurementSystem;
    public PurchaseContract selectedPurchaseContract;

    @Inject
    public FarmViewModel(Context context, MasterRepository masterRepository, FarmRepository farmRepository) {
        this.context = context;
        this.masterRepository = masterRepository;
        this.farmRepository = farmRepository;
    }

    public List<Suppliers> fetchSupplierList() {
        return masterRepository.getSuppliers();
    }

    public List<Warehouses> fetchWarehouseList() {
        return masterRepository.getWarehouses();
    }

    public List<SupplierProducts> fetchSupplierProducts(int sid) {
        return this.masterRepository.getSuppliersProductsListsById(sid);
    }

    public void selectedSupplier(List<Suppliers> suppliersList, List<Suppliers> suppliersFilteredList, Suppliers supplier, int position) {
        this.selectedSupplier = supplier;
        if (!suppliersFilteredList.isEmpty()) {
            for (Suppliers suppliers : suppliersList) {
                suppliers.setSelected(false);
            }
            int i = 0;
            while (i < suppliersFilteredList.size()) {
                suppliersFilteredList.get(i).setSelected(i == position);
                i++;
            }
            return;
        }
        int i2 = 0;
        while (i2 < suppliersList.size()) {
            suppliersList.get(i2).setSelected(i2 == position);
            i2++;
        }
    }

    public void selectedWarehouse(List<Warehouses> warehousesList, List<Warehouses> warehousesFilteredList, Warehouses warehouse, int position) {
        selectedWarehouse = warehouse;
        if (!warehousesFilteredList.isEmpty()) {
            for (Warehouses warehouses : warehousesList) {
                warehouses.setSelected(false);
            }
            int i = 0;
            while (i < warehousesFilteredList.size()) {
                warehousesFilteredList.get(i).setSelected(i == position);
                i++;
            }
            return;
        }
        int i2 = 0;
        while (i2 < warehousesList.size()) {
            warehousesList.get(i2).setSelected(i2 == position);
            i2++;
        }
    }

    public void selectedSupplierProducts(List<SupplierProducts> suppliersProductsList, List<SupplierProducts> suppliersProductsFilteredList, SupplierProducts suppliersProduct, int position) {
        this.selectedSuppliersProducts = suppliersProduct;
        if (!suppliersProductsFilteredList.isEmpty()) {
            for (SupplierProducts supplierProducts : suppliersProductsList) {
                supplierProducts.setSelected(false);
            }
            int i = 0;
            while (i < suppliersProductsFilteredList.size()) {
                suppliersProductsFilteredList.get(i).setSelected(i == position);
                i++;
            }
            return;
        }
        int i2 = 0;
        while (i2 < suppliersProductsList.size()) {
            suppliersProductsList.get(i2).setSelected(i2 == position);
            i2++;
        }
    }

    public void selectedSuppliersProductTypes(List<SupplierProductTypes> suppliersProductTypesList, List<SupplierProductTypes> suppliersProductTypesFilteredList, SupplierProductTypes suppliersProductTypes, int position) {
        this.selectedSuppliersProductTypes = suppliersProductTypes;
        if (!suppliersProductTypesFilteredList.isEmpty()) {
            for (SupplierProductTypes supplierProductTypes : suppliersProductTypesList) {
                supplierProductTypes.setSelected(false);
            }
            int i = 0;
            while (i < suppliersProductTypesFilteredList.size()) {
                suppliersProductTypesFilteredList.get(i).setSelected(i == position);
                i++;
            }
            return;
        }
        int i2 = 0;
        while (i2 < suppliersProductTypesList.size()) {
            suppliersProductTypesList.get(i2).setSelected(i2 == position);
            i2++;
        }
    }

    public void selectedPurchaseContract(List<PurchaseContract> purchaseContractList, List<PurchaseContract> purchaseContractFilteredList, PurchaseContract purchaseContract, int position) {
        this.selectedPurchaseContract = purchaseContract;
        if (!purchaseContractFilteredList.isEmpty()) {
            for (PurchaseContract contract : purchaseContractList) {
                contract.setSelected(false);
            }
            int i = 0;
            while (i < purchaseContractFilteredList.size()) {
                purchaseContractFilteredList.get(i).setSelected(i == position);
                i++;
            }
            return;
        }
        int i2 = 0;
        while (i2 < purchaseContractList.size()) {
            purchaseContractList.get(i2).setSelected(i2 == position);
            i2++;
        }
    }

    public void selectedMeasurementSystem(List<MeasurementSystems> measurementSystemsList, List<MeasurementSystems> measurementSystemsFilteredList, MeasurementSystems measurementSystem, int position) {
        this.selectedMeasurementSystem = measurementSystem;
        if (!measurementSystemsFilteredList.isEmpty()) {
            for (MeasurementSystems measurementSystems : measurementSystemsList) {
                measurementSystems.setSelected(false);
            }
            int i = 0;
            while (i < measurementSystemsFilteredList.size()) {
                measurementSystemsFilteredList.get(i).setSelected(i == position);
                i++;
            }
            return;
        }
        int i2 = 0;
        while (i2 < measurementSystemsList.size()) {
            measurementSystemsList.get(i2).setSelected(i2 == position);
            i2++;
        }
    }

    public List<SupplierProductTypes> fetchSuppliersProductsType(int sid, int productId) {
        return this.masterRepository.getSuppliersProductsTypeListsById(sid, productId);
    }

    public List<PurchaseContract> fetchPurchaseContractList(int supplierId, int productId, List<Integer> productTypes) {
        return this.masterRepository.getPurchaseContractLists(supplierId, productId, productTypes);
    }

    public List<MeasurementSystems> fetchMeasurementSystemList(List<Integer> productTypes) {
        return this.masterRepository.getMeasurementSystemLists(productTypes);
    }

    public long saveFarmDetails(FarmDetails farmDetails) {
        return farmRepository.saveFarmDetails(farmDetails);
    }

    public void saveOrUpdateFarmData(List<FarmDataEntry> farmDataEntryList, String inventoryOrder, int supplierId,
                                     int totalPieces, double totalGrossVolume, double totalNetVolume) {
        farmRepository.saveFarmDataEntry(farmDataEntryList, inventoryOrder, supplierId, totalPieces, totalGrossVolume, totalNetVolume);
    }

    public List<FarmDetails> fetchFarmDetails() {
        return farmRepository.fetchFarmLists();
    }

    public List<FarmDataEntry> getFarmDataEntryLists(String inventoryOrder) {
        return farmRepository.getFarmDataEntryLists(inventoryOrder);
    }
}