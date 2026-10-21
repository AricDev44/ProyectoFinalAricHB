package com.example.proyectofinalarichb.Data.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartItem(
    @PrimaryKey
    val productId: Int,
    val quantity: Int
)