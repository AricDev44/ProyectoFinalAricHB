package com.example.proyectofinalarichb.Data.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dishes")
data class Dish(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name : String,
    val image : Int,
    val portionUnit: String,
    val portionQuantity: Int,
    val calories: Int,
    val protein: Int,
    val carbs: Int,
    val fat: Int
)
