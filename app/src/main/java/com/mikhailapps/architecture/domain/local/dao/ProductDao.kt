package com.mikhailapps.architecture.domain.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mikhailapps.architecture.domain.local.entity.ProductEntity


@Dao
interface ProductDao {

    @Query("SELECT * FROM $PRODUCT_TABLE_NAME")
    fun getProducts(): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(productEntity: ProductEntity)

    companion object {
        const val PRODUCT_TABLE_NAME = "products"

        const val PRODUCT_ID_COLUMN = "productId"
        const val PRODUCT_NAME_COLUMN = "productName"
    }
}
