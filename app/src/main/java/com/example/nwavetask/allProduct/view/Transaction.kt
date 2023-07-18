package com.example.nwavetask.allProduct.view

import com.example.nwavetask.model.Product

interface Transaction {
    fun moveToDetailsScreen(product: Product)
}