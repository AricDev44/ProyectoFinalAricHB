package com.example.proyectofinalarichb.Data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectofinalarichb.Data.Model.Dish
import kotlinx.coroutines.flow.Flow


@Dao
interface DishDao {

    @Query("SELECT * FROM dishes")
    fun getAllDishes(): Flow<List<Dish>>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDish(dish: Dish)

    @Query("SELECT * FROM dishes WHERE id = :id")
    suspend fun getDishById(id: Int): Dish?

    @Query("SELECT COUNT(*) FROM dishes")
    suspend fun getDishCount(): Int
}