package com.mikhailapps.architecture.domain.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mikhailapps.architecture.domain.local.dao.ProductDao.Companion.PRODUCT_ID_COLUMN
import com.mikhailapps.architecture.domain.local.dao.ProductDao.Companion.PRODUCT_NAME_COLUMN
import com.mikhailapps.architecture.domain.local.dao.ProductDao.Companion.PRODUCT_TABLE_NAME

@Entity(tableName = PRODUCT_TABLE_NAME)
data class ProductEntity(
    @PrimaryKey @ColumnInfo(name = PRODUCT_ID_COLUMN) val id: String,
    @ColumnInfo(name = PRODUCT_NAME_COLUMN) val name: String,
)
