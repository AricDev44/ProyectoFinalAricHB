package com.example.proyectofinalarichb.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectofinalarichb.Components.DishCardRow
import com.example.proyectofinalarichb.Components.MainScaffold
import com.example.proyectofinalarichb.Components.PremiumButton
import com.example.proyectofinalarichb.Components.Title
import com.example.proyectofinalarichb.Data.Model.Dish
import com.example.proyectofinalarichb.Data.Repository.DishRepository
import com.example.proyectofinalarichb.Data.Repository.TrackedDishRepository
import com.example.proyectofinalarichb.Data.database.AppDatabase
import com.example.proyectofinalarichb.R
import com.example.proyectofinalarichb.ViewModel.DishViewModel
import com.example.proyectofinalarichb.ViewModel.DishViewModelFactory
import com.example.proyectofinalarichb.ViewModel.NutrientTrackerViewModel
import com.example.proyectofinalarichb.ViewModel.NutrientTrackerViewModelFactory
import com.example.proyectofinalarichb.ui.theme.ProyectoFinalAricHBTheme
import com.example.proyectofinalarichb.ui.theme.TextDM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DishView(
    modifier: Modifier = Modifier,
    onItemSelected: (Int) -> Unit,
    onPremiumButtonClick: () -> Unit = {}
){
    val context = LocalContext.current
    val isInspection = LocalInspectionMode.current

    val dishViewModel: DishViewModel? = if (isInspection) null else {
        val database = AppDatabase.getDataBase(context)
        val repository = DishRepository(database.dishDao())
        val factory = DishViewModelFactory(repository)
        viewModel(factory = factory)
    }

    val trackerViewModel: NutrientTrackerViewModel? = if (isInspection) null else {
        val database = AppDatabase.getDataBase(context)
        val repository = TrackedDishRepository(database.trackedDishDao())
        val factory = NutrientTrackerViewModelFactory(repository)
        viewModel(factory = factory)
    }

    MainScaffold(
        modifier = modifier,
        selectedItem = 1,
        onItemSelected = onItemSelected,
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = {
                        Title(text = "Lista de platillos")
                    }
                )
            }
        },
        floatingActionButton = {
            PremiumButton(onClick = onPremiumButtonClick)
        }
    ) { paddingValues ->
        ContentDishView(
            modifier = Modifier.padding(paddingValues),
            dishViewModel = dishViewModel,
            trackerViewModel = trackerViewModel
        )
    }
}

@Composable
fun ContentDishView(
    modifier: Modifier = Modifier,
    dishViewModel: DishViewModel? = null,
    trackerViewModel: NutrientTrackerViewModel? = null,
    sampleDishes: List<Dish> = emptyList()
){
    val collectedDishes by dishViewModel?.dishes?.collectAsState() ?: remember { mutableStateOf(sampleDishes) }
    val dishes = collectedDishes.ifEmpty { sampleDishes }

    val trackedDishIds by trackerViewModel?.trackedDishIds?.collectAsState() ?: remember { mutableStateOf(emptySet()) }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (dishes.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No hay platillos disponibles",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextDM
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = dishes,
                    key = { it.id }
                ) { dish ->
                    DishCardRow(
                        dish = dish,
                        isAdded = trackedDishIds.contains(dish.id),
                        onAddClick = {
                            trackerViewModel?.addDish(dish)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DishViewPreview() {
    val sampleDishes = listOf(
        Dish(id = 1, name = "Ceviche de pescado blanco", image = R.drawable.ceviche, portionUnit = "g", portionQuantity = 200, calories = 180, protein = 20, carbs = 16, fat = 3),
        Dish(id = 2, name = "Chilaquiles rojos con pollo", image = R.drawable.chilaquiles, portionUnit = "g", portionQuantity = 300, calories = 520, protein = 28, carbs = 42, fat = 22)
    )
    ProyectoFinalAricHBTheme {
        MainScaffold(
            selectedItem = 1,
            onItemSelected = {}
        ) { paddingValues ->
            ContentDishView(
                modifier = Modifier.padding(paddingValues),
                sampleDishes = sampleDishes
            )
        }
    }
}