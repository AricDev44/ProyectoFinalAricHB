package com.example.proyectofinalarichb.Data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectofinalarichb.Data.Model.TrackedDish
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackedDishDao {

    @Query("SELECT * FROM tracked_dishes")
    fun getAllTrackedDishes(): Flow<List<TrackedDish>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrackedDish(trackedDish: TrackedDish)

    @Delete
    suspend fun deleteTrackedDish(trackedDish: TrackedDish)

    @Query("DELETE FROM tracked_dishes WHERE dishId = :dishId")
    suspend fun deleteTrackedDishById(dishId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM tracked_dishes WHERE dishId = :dishId)")
    fun isDishTracked(dishId: Int): Flow<Boolean>

    @Query("DELETE FROM tracked_dishes")
    suspend fun clearAll()
}