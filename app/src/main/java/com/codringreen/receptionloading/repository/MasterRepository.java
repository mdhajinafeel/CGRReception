package com.codringreen.receptionloading.repository;

import com.codringreen.receptionloading.BuildConfig;
import com.codringreen.receptionloading.db.dao.ContainerNumbersDao;
import com.codringreen.receptionloading.db.dao.GirthClassificationDao;
import com.codringreen.receptionloading.db.dao.LengthClassificationDao;
import com.codringreen.receptionloading.db.dao.MeasurementSystemsDao;
import com.codringreen.receptionloading.db.dao.ProductDao;
import com.codringreen.receptionloading.db.dao.ProductTypesDao;
import com.codringreen.receptionloading.db.dao.PurchaseContractDao;
import com.codringreen.receptionloading.db.dao.ShippingLinesDao;
import com.codringreen.receptionloading.db.dao.SupplierProductTypesDao;
import com.codringreen.receptionloading.db.dao.SupplierProductsDao;
import com.codringreen.receptionloading.db.dao.SupplierRolesDao;
import com.codringreen.receptionloading.db.dao.SuppliersDao;
import com.codringreen.receptionloading.db.dao.WarehousesDao;
import com.codringreen.receptionloading.db.entity.ContainerNumbers;
import com.codringreen.receptionloading.db.entity.GirthClassification;
import com.codringreen.receptionloading.db.entity.LengthClassification;
import com.codringreen.receptionloading.db.entity.MeasurementSystems;
import com.codringreen.receptionloading.db.entity.ProductTypes;
import com.codringreen.receptionloading.db.entity.Products;
import com.codringreen.receptionloading.db.entity.PurchaseContract;
import com.codringreen.receptionloading.db.entity.ShippingLines;
import com.codringreen.receptionloading.db.entity.SupplierProductTypes;
import com.codringreen.receptionloading.db.entity.SupplierProducts;
import com.codringreen.receptionloading.db.entity.SupplierRoles;
import com.codringreen.receptionloading.db.entity.Suppliers;
import com.codringreen.receptionloading.db.entity.Warehouses;
import com.codringreen.receptionloading.helper.PreferenceManager;
import com.codringreen.receptionloading.model.response.ContainerNumberMasterResponse;
import com.codringreen.receptionloading.model.response.DownloadMaserResponse;
import com.codringreen.receptionloading.model.response.DownloadMasterDataResponse;
import com.codringreen.receptionloading.model.response.GirthClassificationMasterResponse;
import com.codringreen.receptionloading.model.response.LengthClassificationMasterResponse;
import com.codringreen.receptionloading.model.response.MeasurementSystemMasterResponse;
import com.codringreen.receptionloading.model.response.OriginDataResponse;
import com.codringreen.receptionloading.model.response.OriginResponse;
import com.codringreen.receptionloading.model.response.ProductMasterResponse;
import com.codringreen.receptionloading.model.response.ProductTypeMasterResponse;
import com.codringreen.receptionloading.model.response.PurchaseContractMasterResponse;
import com.codringreen.receptionloading.model.response.ShippingLineMasterResponse;
import com.codringreen.receptionloading.model.response.SupplierMasterResponse;
import com.codringreen.receptionloading.model.response.SupplierProductTypesMasterResponse;
import com.codringreen.receptionloading.model.response.SupplierProductsMasterResponse;
import com.codringreen.receptionloading.model.response.SupplierRolesMasterResponse;
import com.codringreen.receptionloading.model.response.UserDataResponse;
import com.codringreen.receptionloading.model.response.WarehouseMasterResponse;
import com.codringreen.receptionloading.service.api.IReceptionLoadingApiService;
import com.codringreen.receptionloading.service.api.ResponseCallBack;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MasterRepository {

    private final IReceptionLoadingApiService iReceptionLoadingApiService;
    private final ProductDao productDao;
    private final ProductTypesDao productTypesDao;
    private final MeasurementSystemsDao measurementSystemsDao;
    private final PurchaseContractDao purchaseContractDao;
    private final WarehousesDao warehousesDao;
    private final SuppliersDao suppliersDao;
    private final SupplierProductTypesDao supplierProductTypesDao;
    private final ShippingLinesDao shippingLinesDao;
    private final GirthClassificationDao girthClassificationDao;
    private final LengthClassificationDao lengthClassificationDao;
    private final SupplierProductsDao supplierProductsDao;
    private final ContainerNumbersDao containerNumbersDao;
    private final SupplierRolesDao supplierRolesDao;

    public MasterRepository(IReceptionLoadingApiService iReceptionLoadingApiService, ProductDao productDao, ProductTypesDao productTypesDao, MeasurementSystemsDao measurementSystemsDao,
                            PurchaseContractDao purchaseContractDao, WarehousesDao warehousesDao, SuppliersDao suppliersDao, SupplierProductTypesDao supplierProductTypesDao,
                            ShippingLinesDao shippingLinesDao, GirthClassificationDao girthClassificationDao, LengthClassificationDao lengthClassificationDao,
                            SupplierProductsDao supplierProductsDao, ContainerNumbersDao containerNumbersDao, SupplierRolesDao supplierRolesDao) {
        this.iReceptionLoadingApiService = iReceptionLoadingApiService;
        this.productDao = productDao;
        this.productTypesDao = productTypesDao;
        this.measurementSystemsDao = measurementSystemsDao;
        this.purchaseContractDao = purchaseContractDao;
        this.warehousesDao = warehousesDao;
        this.suppliersDao = suppliersDao;
        this.supplierProductTypesDao = supplierProductTypesDao;
        this.shippingLinesDao = shippingLinesDao;
        this.girthClassificationDao = girthClassificationDao;
        this.lengthClassificationDao = lengthClassificationDao;
        this.supplierProductsDao = supplierProductsDao;
        this.containerNumbersDao = containerNumbersDao;
        this.supplierRolesDao = supplierRolesDao;
    }

    public void getOrigins(final ResponseCallBack<List<OriginDataResponse>> callBack) {
        iReceptionLoadingApiService.gerOrigins("application/json").enqueue(new Callback<OriginResponse>() { // from class: com.codringreen.receptionloading.repository.MasterRepository.1
            public void onResponse(Call<OriginResponse> call, Response<OriginResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                    callBack.onSuccess(response.body().getData());
                } else {
                    callBack.onError("ERROR_500");
                }
            }

            public void onFailure(Call<OriginResponse> call, Throwable t) {
                callBack.onError(t.getMessage());
            }
        });
    }

    public void masterDownload(final ResponseCallBack<DownloadMaserResponse> callBack) {
        try {
            iReceptionLoadingApiService.masterDownload(BuildConfig.CONTENT_TYPE).enqueue(new Callback<DownloadMaserResponse>() { // from class: com.codringreen.receptionloading.repository.MasterRepository.2
                public void onResponse(Call<DownloadMaserResponse> call, Response<DownloadMaserResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            if (response.body().isStatus()) {
                                if (response.body().getData() != null) {
                                    deleteMasterData();
                                    UserDataResponse userData = response.body().getData().getUserData();
                                    PreferenceManager.INSTANCE.setKeyOriginId(userData.getOriginId());
                                    PreferenceManager.INSTANCE.setKeyUserid(userData.getUserId());
                                    PreferenceManager.INSTANCE.setKeyName(userData.getFullName());
                                    PreferenceManager.INSTANCE.setKeyPhoto(userData.getPhoto());
                                    PreferenceManager.INSTANCE.setKeyAddress(userData.getAddress());
                                    PreferenceManager.INSTANCE.setKeyEmailid(userData.getEmailId());
                                    PreferenceManager.INSTANCE.setKeyContactno(userData.getContactNo());
                                    saveMasterData(response.body().getData());
                                }
                                callBack.onSuccess(response.body());
                                return;
                            }
                            callBack.onError(response.body().getMessage());
                            return;
                        }
                        callBack.onError("ERROR_500");
                        return;
                    }
                    callBack.onError("ERROR_500");
                }

                public void onFailure(Call<DownloadMaserResponse> call, Throwable t) {
                    callBack.onError(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            callBack.onError(e.getMessage());
        }
    }

    public void deleteMasterData() {
        try {
            productDao.deleteAll();
            productTypesDao.deleteAll();
            measurementSystemsDao.deleteAll();
            shippingLinesDao.deleteAll();
            warehousesDao.deleteAll();
            girthClassificationDao.deleteAll();
            lengthClassificationDao.deleteAll();
            suppliersDao.deleteAll();
            supplierProductsDao.deleteAll();
            supplierProductTypesDao.deleteAll();
            purchaseContractDao.deleteAll();
            containerNumbersDao.deleteAll();
            supplierRolesDao.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveMasterData(DownloadMasterDataResponse data) {
        insertProducts(data.getProducts());
        insertProductTypes(data.getProductTypes());
        insertMeasurementSystems(data.getMeasurementSystems());
        insertShippingLines(data.getShippingLines());
        insertWarehouse(data.getWarehouses());
        insertGirthClassification(data.getGirthClassification());
        insertLengthClassification(data.getLengthClassification());
        insertSuppliers(data.getSuppliers());
        insertPurchaseContracts(data.getPurchaseContract());
        insertContainerNumbers(data.getContainerNumbers());
        //insertContainerDetails(data.getContainers());
        //insertReceptionDetails(data.getReceptions());
    }

    private void insertProducts(List<ProductMasterResponse> products) {
        try {
            List<Products> productsList = new ArrayList<>();
            for (ProductMasterResponse productMasterResponse : products) {
                Products product = new Products();
                product.setProductId(productMasterResponse.getProductId());
                product.setProductName(productMasterResponse.getProductName());
                productsList.add(product);
            }
            productDao.insertOrReplaceProductMasters(productsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertProductTypes(List<ProductTypeMasterResponse> productTypes) {
        try {
            List<ProductTypes> productTypesList = new ArrayList<>();
            for (ProductTypeMasterResponse productTypeMasterResponse : productTypes) {
                ProductTypes productType = new ProductTypes();
                productType.setProductTypeId(productTypeMasterResponse.getProductTypeId());
                productType.setProductTypeName(productTypeMasterResponse.getProductTypeName());
                productTypesList.add(productType);
            }
            productTypesDao.insertOrReplaceProductTypeMasters(productTypesList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertMeasurementSystems(List<MeasurementSystemMasterResponse> measurementSystems) {
        try {
            List<MeasurementSystems> measurementSystemsList = new ArrayList<>();
            for (MeasurementSystemMasterResponse measurementSystemMasterResponse : measurementSystems) {
                MeasurementSystems measurementSystem = new MeasurementSystems();
                measurementSystem.setMeasurementSystemId(measurementSystemMasterResponse.getMeasurementSystemId());
                measurementSystem.setMeasurementSystemName(measurementSystemMasterResponse.getMeasurementSystemName());
                measurementSystem.setProductTypeId(measurementSystemMasterResponse.getProductTypeId());
                measurementSystemsList.add(measurementSystem);
            }
            measurementSystemsDao.insertOrReplaceMeasurementSystems(measurementSystemsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertShippingLines(List<ShippingLineMasterResponse> shippingLines) {
        try {
            List<ShippingLines> shippingLinesList = new ArrayList<>();
            for (ShippingLineMasterResponse shippingLineMasterResponse : shippingLines) {
                ShippingLines shippingLine = new ShippingLines();
                shippingLine.setShippingId(shippingLineMasterResponse.getShippingId());
                shippingLine.setShippingLine(shippingLineMasterResponse.getShippingLine());
                shippingLinesList.add(shippingLine);
            }
            shippingLinesDao.insertOrReplaceShippingLines(shippingLinesList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertWarehouse(List<WarehouseMasterResponse> warehouses) {
        try {
            List<Warehouses> warehousesList = new ArrayList<>();
            for (WarehouseMasterResponse warehouseMasterResponse : warehouses) {
                Warehouses warehouse = new Warehouses();
                warehouse.setWarehouseId(warehouseMasterResponse.getWarehouseId());
                warehouse.setWarehouseName(warehouseMasterResponse.getWarehouseName());
                warehouse.setPol(warehouseMasterResponse.getPol());
                warehousesList.add(warehouse);
            }
            warehousesDao.insertOrReplaceWarehouses(warehousesList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertGirthClassification(List<GirthClassificationMasterResponse> girthClassifications) {
        try {
            List<GirthClassification> girthClassificationList = new ArrayList<>();
            for (GirthClassificationMasterResponse girthClassificationMasterResponse : girthClassifications) {
                GirthClassification girthClassification = new GirthClassification();
                girthClassification.setGirthClassificationId(girthClassificationMasterResponse.getGirthClassificationId());
                girthClassification.setGirthClassification(girthClassificationMasterResponse.getGirthClassification());
                girthClassification.setManual(girthClassificationMasterResponse.isManual());
                girthClassificationList.add(girthClassification);
            }
            girthClassificationDao.insertOrReplaceGirthClassification(girthClassificationList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertLengthClassification(List<LengthClassificationMasterResponse> lengthClassifications) {
        try {
            List<LengthClassification> lengthClassificationList = new ArrayList<>();
            for (LengthClassificationMasterResponse lengthClassificationMasterResponse : lengthClassifications) {
                LengthClassification lengthClassification = new LengthClassification();
                lengthClassification.setLengthClassification(lengthClassificationMasterResponse.getLengthClassification());
                lengthClassification.setLengthClassificationId(lengthClassificationMasterResponse.getLengthClassificationId());
                lengthClassification.setManual(lengthClassificationMasterResponse.isManual());
                lengthClassificationList.add(lengthClassification);
            }
            lengthClassificationDao.insertOrReplaceLengthClassification(lengthClassificationList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertSuppliers(List<SupplierMasterResponse> suppliers) {
        try {
            List<Suppliers> suppliersList = new ArrayList<>();
            List<SupplierRoles> supplierRolesList = new ArrayList<>();
            List<SupplierProducts> supplierProductsList = new ArrayList<>();
            List<SupplierProductTypes> supplierProductTypesList = new ArrayList<>();
            for (SupplierMasterResponse supplierMasterResponse : suppliers) {
                Suppliers supplier = new Suppliers();
                supplier.setSupplierId(supplierMasterResponse.getSupplierId());
                supplier.setSupplierCode(supplierMasterResponse.getSupplierCode());
                supplier.setSupplierName(supplierMasterResponse.getSupplierName());
                suppliersList.add(supplier);
                if (supplierMasterResponse.getSupplierRoles() != null && !supplierMasterResponse.getSupplierRoles().isEmpty()) {
                    for (SupplierRolesMasterResponse supplierRolesMasterResponse : supplierMasterResponse.getSupplierRoles()) {
                        SupplierRoles supplierRoles = new SupplierRoles();
                        supplierRoles.setSupplierId(supplierMasterResponse.getSupplierId());
                        supplierRoles.setSupplierName(supplierMasterResponse.getSupplierName());
                        supplierRoles.setRoleId(supplierRolesMasterResponse.getRoleId());
                        supplierRoles.setSelected(false);
                        supplierRolesList.add(supplierRoles);
                    }
                }
                if (supplierMasterResponse.getSupplierProducts() != null && !supplierMasterResponse.getSupplierProducts().isEmpty()) {
                    for (SupplierProductsMasterResponse supplierProductsMasterResponse : supplierMasterResponse.getSupplierProducts()) {
                        SupplierProducts supplierProducts = new SupplierProducts();
                        supplierProducts.setSupplierId(supplierMasterResponse.getSupplierId());
                        supplierProducts.setSupplierProductId(supplierProductsMasterResponse.getSupplierProductId());
                        supplierProducts.setProductId(supplierProductsMasterResponse.getProductId());
                        supplierProducts.setProductName(supplierProductsMasterResponse.getProductName());
                        supplierProductsList.add(supplierProducts);
                        if (supplierProductsMasterResponse.getSupplierProductTypes() != null && !supplierProductsMasterResponse.getSupplierProductTypes().isEmpty()) {
                            for (SupplierProductTypesMasterResponse supplierProductTypesMasterResponse : supplierProductsMasterResponse.getSupplierProductTypes()) {
                                SupplierProductTypes supplierProductType = new SupplierProductTypes();
                                supplierProductType.setSupplierId(supplierMasterResponse.getSupplierId());
                                supplierProductType.setProductId(supplierProductsMasterResponse.getProductId());
                                supplierProductType.setSupplierProductId(supplierProductsMasterResponse.getSupplierProductId());
                                supplierProductType.setTypeId(supplierProductTypesMasterResponse.getTypeId());
                                supplierProductType.setProductTypeName(supplierProductTypesMasterResponse.getProductTypeName());
                                supplierProductType.setProductTypeId(supplierProductTypesMasterResponse.getProductTypeId());
                                supplierProductTypesList.add(supplierProductType);
                            }
                        }
                    }
                }
            }
            if (!suppliersList.isEmpty()) {
                suppliersDao.insertOrReplaceSuppliers(suppliersList);
                supplierProductsDao.insertOrReplaceSupplierProducts(supplierProductsList);
                supplierProductTypesDao.insertOrReplaceSupplierProductTypes(supplierProductTypesList);
                supplierRolesDao.insertOrReplaceSupplierRoles(supplierRolesList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertPurchaseContracts(List<PurchaseContractMasterResponse> purchaseContracts) {
        try {
            List<PurchaseContract> purchaseContractList = new ArrayList<>();
            for (PurchaseContractMasterResponse purchaseContractMasterResponse : purchaseContracts) {
                PurchaseContract purchaseContract = new PurchaseContract();
                purchaseContract.setContractId(purchaseContractMasterResponse.getContractId());
                purchaseContract.setContractCode(purchaseContractMasterResponse.getContractCode());
                purchaseContract.setPurchaseUnit(purchaseContractMasterResponse.getPurchaseUnit());
                purchaseContract.setCurrency(purchaseContractMasterResponse.getCurrency());
                purchaseContract.setProduct(purchaseContractMasterResponse.getProduct());
                purchaseContract.setProductType(purchaseContractMasterResponse.getProductType());
                purchaseContract.setSupplierId(purchaseContractMasterResponse.getSupplierId());
                purchaseContract.setCircAllowance(purchaseContractMasterResponse.getCircAllowance());
                purchaseContract.setLengthAllowance(purchaseContractMasterResponse.getLengthAllowance());
                purchaseContract.setDescription(purchaseContractMasterResponse.getDescription());
                purchaseContractList.add(purchaseContract);
            }
            purchaseContractDao.insertOrReplacePurchaseContract(purchaseContractList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertContainerNumbers(List<ContainerNumberMasterResponse> containerNumbers) {
        try {
            List<ContainerNumbers> containerNumbersList = new ArrayList<>();
            for (ContainerNumberMasterResponse containerNumberMasterResponse : containerNumbers) {
                ContainerNumbers containerNumber = new ContainerNumbers();
                containerNumber.setContainerNumbers(containerNumberMasterResponse.getContainerNumber());
                containerNumbersList.add(containerNumber);
            }
            containerNumbersDao.insertOrReplaceContainerNumbers(containerNumbersList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void insertContainerDetails(List<ContainerListsDataMasterResponse> containerListsData) {
//        try {
//            List<ContainerDe> arrayList = new ArrayList();
//            for (ContainerListsDataMasterResponse containerListsDataMasterResponse : containerListsData) {
//                ContainerDetails containerDetails = new ContainerDetails();
//                containerDetails.setContainerId(containerListsDataMasterResponse.getContainerId());
//                containerDetails.setContainerNumbers(containerListsDataMasterResponse.getContainerNumber());
//                containerDetails.setProductId(containerListsDataMasterResponse.getProductId());
//                containerDetails.setProductTypeId(containerListsDataMasterResponse.getProductTypeId());
//                containerDetails.setShippingLineId(containerListsDataMasterResponse.getShippingLine());
//                containerDetails.setWarehouseId(containerListsDataMasterResponse.getWarehouseId());
//                containerDetails.setTotalPieces(containerListsDataMasterResponse.getTotalPieces());
//                containerDetails.setTotalGrossVolume(containerListsDataMasterResponse.getTotalGrossVolume());
//                containerDetails.setTotalNetVolume(containerListsDataMasterResponse.getTotalNetVolume());
//                containerDetails.setSealNumber(containerListsDataMasterResponse.getSealNumber());
//                containerDetails.setDispatchDate(containerListsDataMasterResponse.getDispatchDate());
//                containerDetails.setGirthClassificationId(containerListsDataMasterResponse.getCategory());
//                containerDetails.setTempContainerId("");
//                containerDetails.setClosed(containerListsDataMasterResponse.isClosed());
//                containerDetails.setClosedDate(containerListsDataMasterResponse.getClosedDate());
//                containerDetails.setCreatedBy(containerListsDataMasterResponse.getCreatedBy());
//                arrayList.add(containerDetails);
//            }
//            if (arrayList.size() > 0) {
//                this.containerDetailsDao.insertOrReplaceContainerDetails(arrayList);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    private void insertReceptionDetails(List<ReceptionListMasterResponse> receptionList) {
//        try {
//            for (ReceptionListMasterResponse receptionListMasterResponse : receptionList) {
//                Reception reception = new Reception();
//                reception.setReceptionId(receptionListMasterResponse.getReceptionId());
//                reception.setClosed(receptionListMasterResponse.isClosed());
//                reception.setClosedBy(receptionListMasterResponse.getClosedBy());
//                reception.setCreatedBy(receptionListMasterResponse.getCreatedBy());
//                reception.setSupplierId(receptionListMasterResponse.getSupplierId());
//                reception.setProductId(receptionListMasterResponse.getProductId());
//                reception.setProductTypeId(receptionListMasterResponse.getProductTypeId());
//                reception.setContractId(receptionListMasterResponse.getContractId());
//                reception.setSupplierProductId(receptionListMasterResponse.getSupplierProductId());
//                reception.setSupplierProductTypeId(receptionListMasterResponse.getSupplierProductTypeId());
//                reception.setWarehouseId(receptionListMasterResponse.getWarehouseId());
//                reception.setCreateFarm(receptionListMasterResponse.isCreateFarm());
//                reception.setClosedDate(receptionListMasterResponse.getClosedDate());
//                reception.setReceivedDate(receptionListMasterResponse.getReceivedDate());
//                reception.setTempReceptionId("");
//                reception.setInventoryOrder(receptionListMasterResponse.getInventoryOrder());
//                reception.setMeasurementSystem(receptionListMasterResponse.getMeasurementSystemId());
//                reception.setTotalPieces(receptionListMasterResponse.getTotalPieces());
//                reception.setTotalNetVolume(receptionListMasterResponse.getTotalNetVolume());
//                reception.setTotalGrossVolume(receptionListMasterResponse.getTotalGrossVolume());
//                reception.setTruckPlateNumber(receptionListMasterResponse.getTruckPlateNumber());
//                reception.setLogisticCost(receptionListMasterResponse.getLogisticCost());
//                reception.setLogisticPayTo(receptionListMasterResponse.getLogisticPayTo());
//                reception.setSynced(true);
//                if (this.receptionDao.insertOrReplaceReception(reception) > 0) {
//                    ArrayList arrayList = new ArrayList();
//                    ArrayList arrayList2 = new ArrayList();
//                    ArrayList arrayList3 = new ArrayList();
//                    if (receptionListMasterResponse.getReceptionContainerMapping() != null && receptionListMasterResponse.getReceptionContainerMapping().size() > 0) {
//                        this.receptionContainerMappingDao.deleteAllByReception(reception.getReceptionId());
//                        for (ReceptionContainerMappingResponse receptionContainerMappingResponse : receptionListMasterResponse.getReceptionContainerMapping()) {
//                            ReceptionContainerMapping receptionContainerMapping = new ReceptionContainerMapping();
//                            receptionContainerMapping.setReceptionId(receptionContainerMappingResponse.getReceptionId());
//                            receptionContainerMapping.setContainerId(receptionContainerMappingResponse.getContainerId());
//                            receptionContainerMapping.setInventoryOrder(receptionContainerMappingResponse.getInventoryOrder());
//                            receptionContainerMapping.setContainerNumber(receptionContainerMappingResponse.getContainerNumber());
//                            receptionContainerMapping.setTempContainerId("");
//                            receptionContainerMapping.setTempReceptionId("");
//                            receptionContainerMapping.setContainerNumber(receptionContainerMappingResponse.getContainerNumber());
//                            receptionContainerMapping.setClosed(receptionContainerMappingResponse.isClosed());
//                            arrayList3.add(receptionContainerMapping);
//                        }
//                        if (arrayList3.size() > 0) {
//                            this.receptionContainerMappingDao.insertOrReplaceReceptionContainerMapping(arrayList3);
//                        }
//                    }
//                    if (receptionListMasterResponse.getReceptionData() != null && receptionListMasterResponse.getReceptionData().size() > 0) {
//                        for (ReceptionDataListMasterResponse receptionDataListMasterResponse : receptionListMasterResponse.getReceptionData()) {
//                            ReceptionData receptionData = new ReceptionData();
//                            receptionData.setReceptionId(receptionDataListMasterResponse.getReceptionId());
//                            receptionData.setReceptionDataId(receptionDataListMasterResponse.getReceptionDataId());
//                            receptionData.setTempReceptionId("");
//                            receptionData.setInventoryOrder(receptionListMasterResponse.getInventoryOrder());
//                            receptionData.setCreatedBy(receptionDataListMasterResponse.getCreatedBy());
//                            receptionData.setCreatedDate(receptionListMasterResponse.getCreatedDate());
//                            receptionData.setCircumferenceBought(receptionDataListMasterResponse.getCircumferenceBought());
//                            receptionData.setLengthBought(receptionDataListMasterResponse.getLengthBought());
//                            receptionData.setContainerNumber(receptionDataListMasterResponse.getContainerNumber());
//                            receptionData.setPieces(receptionDataListMasterResponse.getPieces());
//                            receptionData.setGrossVolume(receptionDataListMasterResponse.getGrossVolume());
//                            receptionData.setNetVolume(receptionDataListMasterResponse.getNetVolume());
//                            receptionData.setSynced(true);
//                            receptionData.setDeleted(false);
//                            arrayList.add(receptionData);
//                            ContainerData containerData = new ContainerData();
//                            containerData.setContainerId(receptionDataListMasterResponse.getContainerId());
//                            containerData.setInventoryOrder(receptionListMasterResponse.getInventoryOrder());
//                            containerData.setContainerNumber(receptionDataListMasterResponse.getContainerNumber());
//                            containerData.setPieces(receptionDataListMasterResponse.getPieces());
//                            containerData.setCircumferenceBought(receptionDataListMasterResponse.getCircumferenceBought());
//                            containerData.setLengthBought(receptionDataListMasterResponse.getLengthBought());
//                            containerData.setGrossVolume(receptionDataListMasterResponse.getGrossVolume());
//                            containerData.setNetVolume(receptionDataListMasterResponse.getNetVolume());
//                            containerData.setSynced(true);
//                            containerData.setCreatedBy(receptionDataListMasterResponse.getCreatedBy());
//                            containerData.setCreatedDate(receptionListMasterResponse.getCreatedDate());
//                            containerData.setTempContainerId("");
//                            containerData.setDeleted(true);
//                            arrayList2.add(containerData);
//                        }
//                        if (arrayList.size() > 0) {
//                            this.receptionDataDao.insertOrReplaceReceptionData(arrayList);
//                        }
//                        if (arrayList2.size() > 0) {
//                            this.containerDataDao.insertOrReplaceContainerData(arrayList2);
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public List<Suppliers> getSuppliers() {
        return suppliersDao.getSuppliers();
    }

    public List<SupplierRoles> getSupplierRoles() {
        return supplierRolesDao.getSupplierRoles();
    }

    public List<SupplierRoles> getSupplierRoles(int supplierId) {
        return supplierRolesDao.getSupplierRolesById(supplierId);
    }

    public List<SupplierProducts> getSuppliersProductsListsById(int sid) {
        return supplierProductsDao.getSuppliersProductsListsById(sid);
    }

    public List<SupplierProductTypes> getSuppliersProductsTypeListsById(int sid, int productId) {
        return supplierProductTypesDao.getSuppliersProductTypesListsById(productId, sid);
    }

    public List<PurchaseContract> getPurchaseContractLists(int supplierId, int productId, List<Integer> productTypes) {
        return purchaseContractDao.getPurchaseContractLists(supplierId, productId, productTypes);
    }

    public List<MeasurementSystems> getMeasurementSystemLists(List<Integer> productTypes) {
        return measurementSystemsDao.getMeasurementSystemLists(productTypes);
    }

    public List<Warehouses> getWarehouses() {
        return warehousesDao.getWarehouses();
    }

    public List<Products> getProductMastersLists() {
        return productDao.getProductMastersLists();
    }

    public List<ProductTypes> getProductTypeMastersLists() {
        return productTypesDao.getProductTypeMastersLists();
    }

    public List<ShippingLines> getShippingLines() {
        return shippingLinesDao.getShippingLines();
    }

    public List<GirthClassification> getGirthClassifications() {
        return girthClassificationDao.getGirthClassifications();
    }

    public List<LengthClassification> getLengthClassifications() {
        return lengthClassificationDao.getLengthClassifications();
    }

    public int getContainerCount(String containerNumber) {
        return containerNumbersDao.getContainersCount(containerNumber);
    }
}