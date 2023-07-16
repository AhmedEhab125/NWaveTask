package com.example.nwavetask.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProductsHelper{
   const val BASE_URL = "https://www.nweave.com/"
    val retrofitInstance = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}


