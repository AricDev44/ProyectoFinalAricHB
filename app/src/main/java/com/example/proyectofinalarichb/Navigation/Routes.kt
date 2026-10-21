package com.example.proyectofinalarichb.Navigation

sealed class Routes (val route : String) {

    object Login : Routes ("login")

    object Register : Routes ("register")

    object Home : Routes ("home")

    object Dish : Routes ("dish")

    object NutrientTracker : Routes ("nutrienttracker")

    object CloseRestaurants : Routes ("closerrestaurants")

    object Premium : Routes ("premium")

    object Routine : Routes ("routine")
}