package com.example.nwavetask.network

import com.example.nwavetask.model.ProductsModel

interface IRemoteDataSource {
    suspend fun getDataFromApi(): ProductsModel
}