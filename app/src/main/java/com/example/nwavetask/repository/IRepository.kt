package com.example.nwavetask.repository

import com.example.nwavetask.model.Product
import com.example.nwavetask.network.ApiState
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow


interface IRepository {
    suspend fun getProductsFromApi(): ApiState
    suspend fun getProductsFromDataBase(): Flow<List<Product>>
     suspend fun insertProductsIntoDataBase(productModel : List<Product>)
}