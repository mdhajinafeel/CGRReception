package com.codringreen.receptionloading.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.codringreen.receptionloading.db.entity.ContainerNumbers;

import java.util.List;

@Dao
public interface ContainerNumbersDao {

    @Query("DELETE FROM ContainerNumbers")
    void deleteAll();

    @Query("SELECT * FROM ContainerNumbers")
    List<ContainerNumbers> getContainerNumbers();

    @Query("SELECT COUNT(*) AS cnt FROM ContainerNumbers WHERE containerNumbers = :containerNumber")
    int getContainersCount(String containerNumber);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrReplaceContainerNumber(ContainerNumbers containerNumber);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrReplaceContainerNumbers(List<ContainerNumbers> containerNumbers);
}