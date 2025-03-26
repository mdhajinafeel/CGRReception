package com.codringreen.receptionloading.di.modules;

import android.app.Application;

import androidx.room.Room;

import com.codringreen.receptionloading.db.AppExecutors;
import com.codringreen.receptionloading.db.CGRDatabase;
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

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DBModule {

    @Provides
    @Singleton
    CGRDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application, CGRDatabase.class, "cgr_receptionloading.db").allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    @Provides
    @Singleton
    AppExecutors provideAppExecutors() {
        return new AppExecutors();
    }

    @Provides
    @Singleton
    ProductDao provideProductDao(CGRDatabase database) {
        return database.productDao();
    }

    @Provides
    @Singleton
    ProductTypesDao provideProductTypesDao(CGRDatabase database) {
        return database.productTypesDao();
    }

    @Provides
    @Singleton
    MeasurementSystemsDao provideMeasurementSystemsDao(CGRDatabase database) {
        return database.measurementSystemsDao();
    }

    @Provides
    @Singleton
    WarehousesDao provideWarehousesDao(CGRDatabase database) {
        return database.warehousesDao();
    }

    @Provides
    @Singleton
    ShippingLinesDao provideShippingLinesDao(CGRDatabase database) {
        return database.shippingLinesDao();
    }

    @Provides
    @Singleton
    GirthClassificationDao provideGirthClassificationDao(CGRDatabase database) {
        return database.girthClassificationDao();
    }

    @Provides
    @Singleton
    LengthClassificationDao provideLengthClassificationDao(CGRDatabase database) {
        return database.lengthClassificationDao();
    }

    @Provides
    @Singleton
    SuppliersDao provideSuppliersDao(CGRDatabase database) {
        return database.suppliersDao();
    }

    @Provides
    @Singleton
    SupplierProductsDao provideSupplierProductsDao(CGRDatabase database) {
        return database.supplierProductsDao();
    }

    @Provides
    @Singleton
    SupplierProductTypesDao provideSupplierProductTypesDao(CGRDatabase database) {
        return database.supplierProductTypesDao();
    }

    @Provides
    @Singleton
    PurchaseContractDao providePurchaseContractDao(CGRDatabase database) {
        return database.purchaseContractDao();
    }

    @Provides
    @Singleton
    ContainerNumbersDao provideContainerNumbersDao(CGRDatabase database) {
        return database.containerNumbersDao();
    }

    @Provides
    @Singleton
    SupplierRolesDao provideSupplierRolesDao(CGRDatabase database) {
        return database.supplierRolesDao();
    }

    @Provides
    @Singleton
    FarmDetailsDao provideFarmDetailsDao(CGRDatabase database) {
        return database.farmDetailsDao();
    }

    @Provides
    @Singleton
    FarmDataEntryDao provideFarmDataEntryDao(CGRDatabase database) {
        return database.farmDataEntryDao();
    }
}
