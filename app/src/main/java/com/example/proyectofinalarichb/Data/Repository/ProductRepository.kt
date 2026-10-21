package com.example.proyectofinalarichb.Data.Repository

import com.example.proyectofinalarichb.Data.Model.Product
import com.example.proyectofinalarichb.Data.dao.ProductDao
import kotlinx.coroutines.flow.Flow

class ProductRepository (private val productDao: ProductDao) {
    //Obtener todos los productos
    val allProducts: Flow<List<Product>> = productDao.getAllProduct()

    //Insertar nuevos productos
    suspend fun insertProduct(product: Product){
        productDao.insertProduct(product)
    }

    //Obtener la información de los productos
    suspend fun getProductById(id: Int)=productDao.getProductById(id)

    //Conteo total de productos

    suspend fun getProductCount()=productDao.getProductCount()






}