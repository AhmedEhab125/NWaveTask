package com.example.nwavetask.network

object ProductAPi {
    val retrofitService: ProductsApiServices by lazy {
        RetrofitProductsHelper.retrofitInstance.create(ProductsApiServices::class.java)
    }
}