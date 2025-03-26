package com.codringreen.receptionloading.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.codringreen.receptionloading.db.entity.Warehouses;

import java.util.List;
@Dao
public interface WarehousesDao {

    @Query("DELETE FROM Warehouses")
    void deleteAll();

    @Query("SELECT * FROM Warehouses")
    List<Warehouses> getWarehouses();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrReplaceWarehouses(List<Warehouses> warehouses);
}