package com.codringreen.receptionloading.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.codringreen.receptionloading.db.entity.ProductTypes;

import java.util.List;

@Dao
public interface ProductTypesDao {

    @Query("DELETE FROM ProductTypes")
    void deleteAll();

    @Query("SELECT * FROM ProductTypes")
    List<ProductTypes> getProductTypeMastersLists();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrReplaceProductTypeMasters(List<ProductTypes> productTypeMasters);
}