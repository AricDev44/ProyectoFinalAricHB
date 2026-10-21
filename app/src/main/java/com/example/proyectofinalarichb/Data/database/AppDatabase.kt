package com.example.proyectofinalarichb.Data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyectofinalarichb.Data.Model.CartItem
import com.example.proyectofinalarichb.Data.Model.Category
import com.example.proyectofinalarichb.Data.Model.Dish
import com.example.proyectofinalarichb.Data.Model.Product
import com.example.proyectofinalarichb.Data.Model.TrackedDish
import com.example.proyectofinalarichb.Data.dao.CartDao
import com.example.proyectofinalarichb.Data.dao.CategoryDao
import com.example.proyectofinalarichb.Data.dao.DishDao
import com.example.proyectofinalarichb.Data.dao.ProductDao
import com.example.proyectofinalarichb.Data.dao.TrackedDishDao

@Database(
    entities = [CartItem::class, Product::class, Category::class, Dish::class, TrackedDish::class],
    version = 8,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cartDao(): CartDao
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
    abstract fun dishDao(): DishDao
    abstract fun trackedDishDao(): TrackedDishDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDataBase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}