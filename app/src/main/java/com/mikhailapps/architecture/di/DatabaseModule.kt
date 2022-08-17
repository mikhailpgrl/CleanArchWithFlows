package com.mikhailapps.architecture.di

import android.content.Context
import androidx.room.Room
import com.mikhailapps.architecture.AppDataBase
import com.mikhailapps.architecture.AppDataBase.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(appContext, AppDataBase::class.java, DATABASE_NAME).build()
    }

    @Provides
    fun provideProductDao(appDataBase: AppDataBase) = appDataBase.productDao()
}