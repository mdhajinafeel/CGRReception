package com.codringreen.receptionloading.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.codringreen.receptionloading.db.dao.ContainerNumbersDao;
import com.codringreen.receptionloading.db.dao.FarmDataEntryDao;
import com.codringreen.receptionloading.db.dao.FarmDetailsDao;
import com.codringreen.receptionloading.db.entity.ContainerNumbers;
import com.codringreen.receptionloading.db.entity.FarmDataEntry;
import com.codringreen.receptionloading.db.entity.FarmDetails;
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


@Database(entities = {ContainerNumbers.class, GirthClassification.class, LengthClassification.class, MeasurementSystems.class,
        Products.class, ProductTypes.class, PurchaseContract.class, ShippingLines.class, SupplierProducts.class, SupplierProductTypes.class,
        SupplierRoles.class, Suppliers.class, Warehouses.class, FarmDetails.class, FarmDataEntry.class},
        version = 1, exportSchema = false)
public abstract class CGRDatabase extends RoomDatabase {
    public abstract ContainerNumbersDao containerNumbersDao();

    public abstract GirthClassificationDao girthClassificationDao();

    public abstract LengthClassificationDao lengthClassificationDao();

    public abstract MeasurementSystemsDao measurementSystemsDao();

    public abstract ProductDao productDao();

    public abstract ProductTypesDao productTypesDao();

    public abstract PurchaseContractDao purchaseContractDao();

    public abstract ShippingLinesDao shippingLinesDao();

    public abstract SupplierProductTypesDao supplierProductTypesDao();

    public abstract SupplierProductsDao supplierProductsDao();

    public abstract SupplierRolesDao supplierRolesDao();

    public abstract SuppliersDao suppliersDao();

    public abstract WarehousesDao warehousesDao();

    public abstract FarmDetailsDao farmDetailsDao();

    public abstract FarmDataEntryDao farmDataEntryDao();
}