package com.example.nwavetask.allProduct

import com.example.nwavetask.model.Product

interface Transaction {
    fun moveToDetailsScreen(product: Product)
}