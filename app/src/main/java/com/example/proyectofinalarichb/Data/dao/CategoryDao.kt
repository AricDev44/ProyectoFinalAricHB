package com.example.proyectofinalarichb.Data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.proyectofinalarichb.Data.Model.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    fun getCategories(): Flow<List<Category>>

    @Query("SELECT COUNT (*) FROM categories")
    suspend fun countCategories(): Int

    @Insert
    suspend fun insertCategories(categories: List<Category>): List<Long>
}
