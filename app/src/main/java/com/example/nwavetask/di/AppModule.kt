package com.example.nwavetask.di

import com.example.nwavetask.network.ProductAPi
import com.example.nwavetask.network.ProductsApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
}