package com.codringreen.receptionloading.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.codringreen.receptionloading.db.entity.MeasurementSystems;

import java.util.List;

@Dao
public interface MeasurementSystemsDao {

    @Query("DELETE FROM MeasurementSystems")
    void deleteAll();

    @Query("SELECT * FROM MeasurementSystems WHERE productTypeId IN (:productTypes)")
    List<MeasurementSystems> getMeasurementSystemLists(List<Integer> productTypes);

    @Query("SELECT * FROM MeasurementSystems")
    List<MeasurementSystems> getMeasurementSystems();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrReplaceMeasurementSystems(List<MeasurementSystems> measurementSystems);
}