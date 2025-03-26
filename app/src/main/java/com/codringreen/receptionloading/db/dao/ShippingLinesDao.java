package com.codringreen.receptionloading.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.codringreen.receptionloading.db.entity.ShippingLines;

import java.util.List;

@Dao
public interface ShippingLinesDao {

    @Query("DELETE FROM ShippingLines")
    void deleteAll();

    @Query("SELECT * FROM ShippingLines")
    List<ShippingLines> getShippingLines();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrReplaceShippingLines(List<ShippingLines> shippingLines);
}