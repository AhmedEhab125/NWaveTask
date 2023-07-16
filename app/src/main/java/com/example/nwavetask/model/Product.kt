package com.example.nwavetask.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Products")
data class Product(
    val description: String,
    @PrimaryKey
    val id: String,
    var image_url: String,
    val name: String,
    val price: String,
) : java.io.Serializable