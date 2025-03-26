package com.codringreen.receptionloading.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.codringreen.receptionloading.db.entity.Products;

import java.util.List;
@Dao
public interface ProductDao {

    @Query("DELETE FROM Products")
    void deleteAll();

    @Query("SELECT * FROM Products")
    List<Products> getProductMastersLists();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrReplaceProductMasters(List<Products> productMasters);
}