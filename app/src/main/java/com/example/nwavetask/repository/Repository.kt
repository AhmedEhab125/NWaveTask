package com.example.nwavetask.repository

import com.example.nwavetask.DataBase.ILocalDataSource
import com.example.nwavetask.model.Product
import com.example.nwavetask.network.ApiState
import com.example.nwavetask.network.IRemoteDataSource
import com.example.nwavetask.network.ProductsApiServices
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor( private val remote: IRemoteDataSource, private val localData: ILocalDataSource): IRepository{
    override suspend fun getProductsFromApi(): ApiState {
        var products = remote.getDataFromApi()
     var productList =   products.map {
            it.Product
        }
        insertProductsIntoDataBase(productList)
       return ApiState.Success(products)
    }

    override suspend fun getProductsFromDataBase(): Flow<List<Product>> {
     return localData.getProductsFromDatabase()
    }

     override suspend fun insertProductsIntoDataBase(productList: List<Product>) {
       localData.insertProductsIntoDatabase(productList)
    }

}