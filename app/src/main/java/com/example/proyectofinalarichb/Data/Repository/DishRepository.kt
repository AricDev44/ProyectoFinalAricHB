package com.example.proyectofinalarichb.Data.Repository

import com.example.proyectofinalarichb.Data.Model.Dish
import com.example.proyectofinalarichb.Data.dao.DishDao
import kotlinx.coroutines.flow.Flow

class DishRepository (private val dishDao: DishDao) {

    val allDishes: Flow<List<Dish>> = dishDao.getAllDishes()

    suspend fun insertDish(dish: Dish){
        dishDao.insertDish(dish)
    }

    suspend fun getDishById(id: Int) = dishDao.getDishById(id)

    suspend fun getDishCount() = dishDao.getDishCount()
}