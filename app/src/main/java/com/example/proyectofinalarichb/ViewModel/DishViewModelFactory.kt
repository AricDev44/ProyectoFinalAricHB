package com.example.proyectofinalarichb.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyectofinalarichb.Data.Repository.DishRepository

class DishViewModelFactory(
    private val repository: DishRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        if(modelClass.isAssignableFrom(DishViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return DishViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}