package com.example.proyectofinalarichb.Data.Repository

import com.example.proyectofinalarichb.Data.dao.CategoryDao
import com.example.proyectofinalarichb.Data.Model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryRepository(private val categoryDao: CategoryDao) {
    fun getCategories(): Flow<List<Category>> {
        return categoryDao.getCategories()
    }

    suspend fun insertCategories(categories: List<Category>) = withContext(Dispatchers.IO) {
        categoryDao.insertCategories(categories)
    }

    suspend fun countCategories(): Int = withContext(Dispatchers.IO) {
        categoryDao.countCategories()
    }
}
