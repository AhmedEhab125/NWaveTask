package com.example.nwavetask.network

import com.example.nwavetask.model.ProductsModel
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApiServices {
    @GET("wp-content/uploads/2012/09/featured.txt")
    suspend fun getProducts() : Response<ProductsModel>
}