package com.example.proyectofinalarichb.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalarichb.Data.Model.Dish
import com.example.proyectofinalarichb.Data.Model.TrackedDish
import com.example.proyectofinalarichb.Data.Repository.TrackedDishRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class NutrientSummary(
    val totalCalories: Int = 0,
    val totalProtein: Int = 0,
    val totalCarbs: Int = 0,
    val totalFat: Int = 0
)

class NutrientTrackerViewModel(private val repository: TrackedDishRepository) : ViewModel() {

    val trackedDishes: StateFlow<List<TrackedDish>> = repository.allTrackedDishes
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val trackedDishIds: StateFlow<Set<Int>> = repository.allTrackedDishes
        .map { list -> list.map { it.dishId }.toSet() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptySet())

    val nutrientSummary: StateFlow<NutrientSummary> = repository.allTrackedDishes
        .map { list ->
            NutrientSummary(
                totalCalories = list.sumOf { it.calories },
                totalProtein = list.sumOf { it.protein },
                totalCarbs = list.sumOf { it.carbs },
                totalFat = list.sumOf { it.fat }
            )
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NutrientSummary())

    fun addDish(dish: Dish) {
        viewModelScope.launch {
            repository.addDish(dish)
        }
    }

    fun removeDish(dishId: Int) {
        viewModelScope.launch {
            repository.removeDish(dishId)
        }
    }
}