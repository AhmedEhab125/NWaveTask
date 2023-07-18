package com.example.nwavetask.di

import android.content.Context
import com.example.nwavetask.DataBase.AppDatabase
import com.example.nwavetask.DataBase.ProductDao
import com.example.nwavetask.network.ProductAPi
import com.example.nwavetask.network.ProductsApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideProductApi() : ProductsApiServices{
        return ProductAPi.retrofitService
    }
    @Provides
    @Singleton
    fun getProductDaoInstance(@ApplicationContext context: Context): ProductDao {
        return AppDatabase.getInstance(context).productDao()
    }
}