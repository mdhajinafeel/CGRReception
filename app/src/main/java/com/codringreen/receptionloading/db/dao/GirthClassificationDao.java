package com.codringreen.receptionloading.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.codringreen.receptionloading.db.entity.GirthClassification;

import java.util.List;

@Dao
public interface GirthClassificationDao {

    @Query("DELETE FROM GirthClassification")
    void deleteAll();

    @Query("SELECT * FROM GirthClassification")
    List<GirthClassification> getGirthClassifications();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrReplaceGirthClassification(List<GirthClassification> girthClassifications);
}