package com.example.nwavetask.repository

import com.example.nwavetask.network.ProductsApiServices
import javax.inject.Inject

class Repository @Inject constructor (private val productsApiServices: ProductsApiServices): IRepository{

}