package com.example.proyectofinalarichb.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectofinalarichb.Components.MainScaffold
import com.example.proyectofinalarichb.Components.PremiumButton
import com.example.proyectofinalarichb.Components.SpaceV
import com.example.proyectofinalarichb.Components.Title
import com.example.proyectofinalarichb.Components.TrackedDishCard
import com.example.proyectofinalarichb.Data.Model.TrackedDish
import com.example.proyectofinalarichb.Data.Repository.TrackedDishRepository
import com.example.proyectofinalarichb.Data.database.AppDatabase
import com.example.proyectofinalarichb.R
import com.example.proyectofinalarichb.ViewModel.NutrientSummary
import com.example.proyectofinalarichb.ViewModel.NutrientTrackerViewModel
import com.example.proyectofinalarichb.ViewModel.NutrientTrackerViewModelFactory
import com.example.proyectofinalarichb.ui.theme.KeyOrangeDM
import com.example.proyectofinalarichb.ui.theme.ProyectoFinalAricHBTheme
import com.example.proyectofinalarichb.ui.theme.TextDM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutrientTrackerView(
    modifier: Modifier = Modifier,
    onItemSelected: (Int) -> Unit,
    onPremiumButtonClick: () -> Unit = {}
){
    val context = LocalContext.current
    val isInspection = LocalInspectionMode.current

    val trackerViewModel: NutrientTrackerViewModel? = if (isInspection) null else {
        val database = AppDatabase.getDataBase(context)
        val repository = TrackedDishRepository(database.trackedDishDao())
        val factory = NutrientTrackerViewModelFactory(repository)
        viewModel(factory = factory)
    }

    MainScaffold(
        modifier = modifier,
        selectedItem = 2,
        onItemSelected = onItemSelected,
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = {
                        Title(text = "Calculadora de calorías y macros")
                    }
                )
            }
        },
        floatingActionButton = {
            PremiumButton(onClick = onPremiumButtonClick)
        }
    ) { paddingValues ->
        ContentNutrientTrackerView(
            modifier = Modifier.padding(paddingValues),
            trackerViewModel = trackerViewModel
        )
    }
}

@Composable
fun ContentNutrientTrackerView(
    modifier: Modifier = Modifier,
    trackerViewModel: NutrientTrackerViewModel? = null,
    sampleTrackedDishes: List<TrackedDish> = emptyList(),
    sampleSummary: NutrientSummary = NutrientSummary()
){
    val collectedDishes by trackerViewModel?.trackedDishes?.collectAsState() ?: remember { mutableStateOf(sampleTrackedDishes) }
    val trackedDishes = if (collectedDishes.isNotEmpty()) collectedDishes else sampleTrackedDishes

    val collectedSummary by trackerViewModel?.nutrientSummary?.collectAsState() ?: remember { mutableStateOf(sampleSummary) }
    val summary = if (collectedDishes.isNotEmpty()) collectedSummary else (if (sampleTrackedDishes.isNotEmpty()) sampleSummary else collectedSummary)

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Platillos consumidos hoy",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextDM,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            if (trackedDishes.isEmpty()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Aún no has agregado platillos hoy.\nVe a la sección 'Platillos' para agregar tu comida.",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextDM.copy(alpha = 0.8f),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        items = trackedDishes,
                        key = { it.dishId }
                    ) { trackedDish ->
                        TrackedDishCard(
                            trackedDish = trackedDish,
                            onRemoveClick = {
                                trackerViewModel?.removeDish(trackedDish.dishId)
                            }
                        )
                    }
                }
            }

            SpaceV(12)

            NutrientSummaryCard(summary = summary)
        }
    }
}

@Composable
fun NutrientSummaryCard(summary: NutrientSummary) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = KeyOrangeDM
        ),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Consumo Diario Total",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextDM
            )

            SpaceV(12)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                NutrientStatItem(label = "Calorías", value = "${summary.totalCalories} kcal")
                NutrientStatItem(label = "Proteínas", value = "${summary.totalProtein} g")
                NutrientStatItem(label = "Carbs", value = "${summary.totalCarbs} g")
                NutrientStatItem(label = "Grasas", value = "${summary.totalFat} g")
            }
        }
    }
}

@Composable
fun NutrientStatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = TextDM.copy(alpha = 0.8f)
        )
        SpaceV(4)
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = TextDM
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NutrientTrackerViewPreview() {
    val sampleDishes = listOf(
        TrackedDish(
            dishId = 1,
            name = "Ceviche de pescado blanco",
            image = R.drawable.ceviche,
            portionUnit = "g",
            portionQuantity = 200,
            calories = 180,
            protein = 20,
            carbs = 16,
            fat = 3
        ),
        TrackedDish(
            dishId = 2,
            name = "Chilaquiles rojos con pollo",
            image = R.drawable.chilaquiles,
            portionUnit = "g",
            portionQuantity = 300,
            calories = 520,
            protein = 28,
            carbs = 42,
            fat = 22
        )
    )
    val sampleSummary = NutrientSummary(
        totalCalories = 700,
        totalProtein = 48,
        totalCarbs = 58,
        totalFat = 25
    )

    ProyectoFinalAricHBTheme {
        MainScaffold(
            selectedItem = 2,
            onItemSelected = {}
        ) { paddingValues ->
            ContentNutrientTrackerView(
                modifier = Modifier.padding(paddingValues),
                sampleTrackedDishes = sampleDishes,
                sampleSummary = sampleSummary
            )
        }
    }
}