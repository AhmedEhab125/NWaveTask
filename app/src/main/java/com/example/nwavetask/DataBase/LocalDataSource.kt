package com.example.nwavetask.DataBase

import com.example.nwavetask.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val local: ProductDao) : ILocalDataSource {


    override suspend fun getProductsFromDatabase() = local.getProductsFromDataBase()

    override suspend fun insertProductsIntoDatabase(productModel: List<Product>) {
        local.insertProductsDataIntoDataBase(productModel)
    }
}