package com.example.nwavetask.allProducts

import com.example.nwavetask.model.Product

interface Transaction {
    fun moveToDetailsScreen(product: Product)
}