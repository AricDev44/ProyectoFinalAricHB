package com.example.proyectofinalarichb.Data.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name : String,
    val image : Int,
    val price : Float,
    val weight : String,
    val isOffer: Boolean,
)
