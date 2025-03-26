package com.codringreen.receptionloading.di.modules;

import com.codringreen.receptionloading.db.dao.ContainerNumbersDao;
import com.codringreen.receptionloading.db.dao.FarmDataEntryDao;
import com.codringreen.receptionloading.db.dao.FarmDetailsDao;
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
import com.codringreen.receptionloading.repository.FarmRepository;
import com.codringreen.receptionloading.repository.LoginRepository;
import com.codringreen.receptionloading.repository.MasterRepository;
import com.codringreen.receptionloading.service.api.IAuthApiService;
import com.codringreen.receptionloading.service.api.IReceptionLoadingApiService;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module(includes = {ApiModule.class, DBModule.class})
public class RepoModule {

    @Provides
    @Singleton
    LoginRepository provideLoginRepository(IAuthApiService iAuthApiService) {
        return new LoginRepository(iAuthApiService);
    }

    @Provides
    @Singleton
    MasterRepository provideMasterRepository(IReceptionLoadingApiService iReceptionLoadingApiService, ProductDao productDao, ProductTypesDao productTypesDao, MeasurementSystemsDao measurementSystemsDao,
                                             PurchaseContractDao purchaseContractDao, WarehousesDao warehousesDao, SuppliersDao suppliersDao, SupplierProductTypesDao supplierProductTypesDao,
                                             ShippingLinesDao shippingLinesDao, GirthClassificationDao girthClassificationDao, LengthClassificationDao lengthClassificationDao,
                                             SupplierProductsDao supplierProductsDao, ContainerNumbersDao containerNumbersDao, SupplierRolesDao supplierRolesDao) {
        return new MasterRepository(iReceptionLoadingApiService, productDao, productTypesDao, measurementSystemsDao,
                purchaseContractDao, warehousesDao, suppliersDao, supplierProductTypesDao,
                shippingLinesDao, girthClassificationDao, lengthClassificationDao,
                supplierProductsDao, containerNumbersDao, supplierRolesDao);
    }

    @Provides
    @Singleton
    FarmRepository provideFarmRepository(FarmDetailsDao farmDetailsDao, FarmDataEntryDao farmDataEntryDao) {
        return new FarmRepository(farmDetailsDao, farmDataEntryDao);
    }
}