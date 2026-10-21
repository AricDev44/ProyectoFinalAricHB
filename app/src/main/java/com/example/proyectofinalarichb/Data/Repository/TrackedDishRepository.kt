package com.example.proyectofinalarichb.Data.Repository

import com.example.proyectofinalarichb.Data.Model.Dish
import com.example.proyectofinalarichb.Data.Model.TrackedDish
import com.example.proyectofinalarichb.Data.dao.TrackedDishDao
import kotlinx.coroutines.flow.Flow

class TrackedDishRepository(private val trackedDishDao: TrackedDishDao) {

    val allTrackedDishes: Flow<List<TrackedDish>> = trackedDishDao.getAllTrackedDishes()

    suspend fun addDish(dish: Dish) {
        val tracked = TrackedDish(
            dishId = dish.id,
            name = dish.name,
            image = dish.image,
            portionUnit = dish.portionUnit,
            portionQuantity = dish.portionQuantity,
            calories = dish.calories,
            protein = dish.protein,
            carbs = dish.carbs,
            fat = dish.fat
        )
        trackedDishDao.insertTrackedDish(tracked)
    }

    suspend fun removeDish(dishId: Int) {
        trackedDishDao.deleteTrackedDishById(dishId)
    }

    fun isDishTracked(dishId: Int): Flow<Boolean> {
        return trackedDishDao.isDishTracked(dishId)
    }
}