package com.example.proyectofinalarichb.Components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun BottomMenu (

    selectedItem: Int,
    onItemSelected: (Int) -> Unit
   ){
    val navItemColors = NavigationBarItemDefaults.colors(
        selectedIconColor = MaterialTheme.colorScheme.primary,
        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        selectedTextColor = MaterialTheme.colorScheme.primary,
        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
    )

    NavigationBar {
        NavigationBarItem(
            selected = selectedItem == 0,
            onClick = {
                onItemSelected(0)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription="Menú"
                )
            },

            label = {
                Text("Menú")
            },
            colors = navItemColors
        )
        NavigationBarItem(
            selected = selectedItem == 1,
            onClick = {
                onItemSelected(1)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Fastfood,
                    contentDescription="Lista de platillos"
                )
            },
            label = {
                Text("Platillos")
            },
            colors = navItemColors
        )

        NavigationBarItem(
            selected = selectedItem == 2,
            onClick = {
                onItemSelected(2)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Calculate,
                    contentDescription = "Calculadora"
                )
            },
            label = {
                Text("Calculadora de Macros")
            },
            colors = navItemColors
        )
        NavigationBarItem(
            selected = selectedItem == 3,
            onClick = {
                onItemSelected(3)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Restaurant,
                    contentDescription = "Restaurantes Cercanos"
                )
            },
            label = {
                Text("Restaurantes Cercanos")
            },
            colors = navItemColors
        )
    }
}