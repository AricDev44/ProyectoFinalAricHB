package com.example.proyectofinalarichb.Data.Repository

import com.example.proyectofinalarichb.Data.Model.CartItem
import com.example.proyectofinalarichb.Data.dao.CartDao
import kotlinx.coroutines.flow.Flow

class CartRepository (private val cartDao: CartDao) {
    val cartItems: Flow<List<CartItem>> = cartDao.getCartItems()

    //Agregar item al carrito
    suspend fun addItem(productId: Int){
        //comparamos el producto por su id para ver si existe
        val existingItem = cartDao.getCartItemById(productId)
        if (existingItem != null){
            cartDao.insertOrUpdate(existingItem.copy(quantity = existingItem.quantity+1))
        }else{
            cartDao.insertOrUpdate(CartItem(productId,1))
        }
    }

    //Actualizar cantidad de productos en el carrito

    suspend fun updateQuantity(productId: Int, newQuantity: Int){
        if (newQuantity <=0){
            cartDao.getCartItemById(productId)?.let { cartDao.deleteCartItem(it) }
        } else{
            cartDao.insertOrUpdate(CartItem(productId,newQuantity))
        }
    }

    //borrar elementos del carrito

    suspend fun deleteItem(productId: Int){
        cartDao.getCartItemById(productId)?.let { cartDao.deleteCartItem(it) }
    }

    //vaciar carrito completo
    suspend fun clearCart(){
        cartDao.clearCart()
    }
}