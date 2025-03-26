package com.codringreen.receptionloading.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.codringreen.receptionloading.db.entity.FarmDataEntry;
import com.codringreen.receptionloading.db.entity.FarmDetails;

import java.util.List;

@Dao
public interface FarmDataEntryDao {

    @Query("DELETE FROM FarmDataEntry")
    void deleteAll();

    @Query("DELETE FROM FarmDataEntry WHERE inventoryOrder = :inventoryOrder")
    void deleteFarmData(String inventoryOrder);

    @Query("SELECT * FROM FarmDataEntry")
    List<FarmDataEntry> getFarmDataEntry();

    @Query("SELECT * FROM FarmDataEntry WHERE inventoryOrder = :inventoryOrder")
    List<FarmDataEntry> getFarmDataEntry(String inventoryOrder);

    @Query("SELECT COUNT(*) AS cnt FROM FarmDataEntry WHERE inventoryOrder = :inventoryOrder")
    int getFarmDataCount(String inventoryOrder);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertOrReplaceFarmDataEntry(FarmDataEntry farmDataEntry);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrReplaceFarmDataEntry(List<FarmDataEntry> farmDataEntryList);
}