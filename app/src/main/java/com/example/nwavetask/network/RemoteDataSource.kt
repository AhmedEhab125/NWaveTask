package com.example.nwavetask.network

import com.example.nwavetask.model.ProductsModel
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val myApi:ProductsApiServices) : IRemoteDataSource {
    override suspend fun getDataFromApi(): ProductsModel {
        return myApi.getProducts().body()!!
    }
}