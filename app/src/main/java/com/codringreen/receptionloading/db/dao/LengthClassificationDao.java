package com.codringreen.receptionloading.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.codringreen.receptionloading.db.entity.LengthClassification;

import java.util.List;

@Dao
public interface LengthClassificationDao {

    @Query("DELETE FROM LengthClassification")
    void deleteAll();

    @Query("SELECT * FROM LengthClassification")
    List<LengthClassification> getLengthClassifications();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrReplaceLengthClassification(List<LengthClassification> lengthClassifications);
}