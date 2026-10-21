package com.example.proyectofinalarichb.Data.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracked_dishes")
data class TrackedDish(
    @PrimaryKey val dishId: Int,
    val name: String,
    val image: Int,
    val portionUnit: String,
    val portionQuantity: Int,
    val calories: Int,
    val protein: Int,
    val carbs: Int,
    val fat: Int
)