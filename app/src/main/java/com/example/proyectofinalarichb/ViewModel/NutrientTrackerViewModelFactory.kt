package com.example.proyectofinalarichb.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyectofinalarichb.Data.Repository.TrackedDishRepository

class NutrientTrackerViewModelFactory(
    private val repository: TrackedDishRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NutrientTrackerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NutrientTrackerViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class: ${modelClass.name}")
    }
}