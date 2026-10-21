package com.example.proyectofinalarichb.ViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalarichb.Data.Model.Dish
import com.example.proyectofinalarichb.Data.Repository.DishRepository
import com.example.proyectofinalarichb.R
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DishViewModel (private val repository: DishRepository): ViewModel() {

    val dishes: StateFlow<List<Dish>> = repository.allDishes
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),emptyList())
    init {
        viewModelScope.launch {
            checkAndInsertInitialData()
        }
    }

    private fun checkAndInsertInitialData(){
        viewModelScope.launch {
            if (repository.getDishCount()==0){
                val initialDishes = listOf(
                    Dish(name= "Ceviche de pescado blanco", image = R.drawable.ceviche, portionUnit = "g", portionQuantity = 200, calories = 180, protein = 20, carbs = 16, fat = 3),
                    Dish(name= "Chilaquiles rojos con pollo", image = R.drawable.chilaquiles, portionUnit = "g", portionQuantity = 300, calories = 520, protein = 28, carbs = 42, fat = 22),
                    Dish(name= "Huarache de bistec", image = R.drawable.huabistec, portionUnit = "g", portionQuantity = 250, calories = 480, protein = 32, carbs = 45, fat = 18),
                    Dish(name= "Sopa de lentejas", image = R.drawable.lentejas, portionUnit = "g", portionQuantity = 240, calories = 190, protein = 12, carbs = 34, fat = 2),
                    Dish(name= "Nopales con queso panela", image = R.drawable.nopales, portionUnit = "g", portionQuantity = 150, calories = 250, protein = 12, carbs = 6, fat = 6),
                    Dish(name= "Quesadillas de requesón al comal", image = R.drawable.quesadillas, portionUnit = "g", portionQuantity = 200, calories = 180, protein = 20, carbs = 16, fat = 3),
                    Dish(name= "Tacos de pollo al comal", image = R.drawable.tacospollo, portionUnit = "tacos", portionQuantity = 2, calories = 320, protein = 36, carbs = 30, fat = 6)
                )
                initialDishes.forEach { repository.insertDish(it)}
            }
        }
    }
}