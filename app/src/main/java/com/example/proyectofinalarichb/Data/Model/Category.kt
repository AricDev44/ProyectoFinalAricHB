package com.example.proyectofinalarichb.Data.Model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val imageName: String,
    val colorValue: Long
)

val Category.color: Color
    get() = Color(this.colorValue.toULong())
