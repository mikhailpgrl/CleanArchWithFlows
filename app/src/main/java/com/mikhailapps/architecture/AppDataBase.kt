package com.mikhailapps.architecture

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mikhailapps.architecture.domain.local.dao.ProductDao
import com.mikhailapps.architecture.domain.local.entity.ProductEntity


@Database(
    version = 1,
    entities = [ProductEntity::class],
    exportSchema = true
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        const val DATABASE_NAME = "my_database_db"
    }
}