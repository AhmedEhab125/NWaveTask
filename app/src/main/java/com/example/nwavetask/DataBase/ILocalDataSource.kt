package com.example.nwavetask.DataBase

import com.example.nwavetask.model.Product
import kotlinx.coroutines.flow.Flow

interface ILocalDataSource {
    suspend fun getProductsFromDatabase(): Flow<List<Product>>
    suspend fun insertProductsIntoDatabase(productModel : List<Product>)
}