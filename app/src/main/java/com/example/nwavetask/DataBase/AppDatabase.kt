package com.example.nwavetask.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nwavetask.model.Product

@Database([Product::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun productDao():ProductDao
    companion object{
        @Volatile
        private var myInstance:AppDatabase? = null
        fun getInstance(context: Context):AppDatabase{
            return myInstance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, "products"
                ).build()
                myInstance = instance
                instance
            }
        }
    }
}