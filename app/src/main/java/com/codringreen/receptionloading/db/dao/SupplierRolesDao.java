package com.codringreen.receptionloading.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.codringreen.receptionloading.db.entity.SupplierRoles;

import java.util.List;

@Dao
public interface SupplierRolesDao {

    @Query("DELETE FROM SupplierRoles")
    void deleteAll();

    @Query("SELECT * FROM SupplierRoles")
    List<SupplierRoles> getSupplierRoles();

    @Query("SELECT * FROM SupplierRoles WHERE supplierId = :sid")
    List<SupplierRoles> getSupplierRolesById(int sid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrReplaceSupplierRoles(List<SupplierRoles> supplierRoles);
}