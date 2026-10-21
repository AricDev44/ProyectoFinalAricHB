package com.example.proyectofinalarichb.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinalarichb.Components.CardViewHome
import com.example.proyectofinalarichb.Components.MainScaffold
import com.example.proyectofinalarichb.Components.SpaceV
import com.example.proyectofinalarichb.Components.Title
import com.example.proyectofinalarichb.R
import com.example.proyectofinalarichb.ui.theme.ProyectoFinalAricHBTheme
import com.example.proyectofinalarichb.ui.theme.TextDM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoutineView(
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit = {}
) {
    MainScaffold(
        modifier = modifier,
        selectedItem = -1,
        onItemSelected = {},
        showBottomBar = false,
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = {
                        Title(text = "Rutinas")
                    }
                )
            }
        }
    ) { paddingValues ->
        ContentRoutineView(
            modifier = Modifier.padding(paddingValues),
            onNavigateToHome = onNavigateToHome
        )
    }
}

@Composable
fun ContentRoutineView(
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit = {}
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Rutinas de Ejercicio",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = TextDM
            )

            SpaceV(24)

            CardViewHome(
                title = "Rutina de Push",
                description = "2 horas - 8 ejercicios de pecho, tricep y hombro",
                image = R.drawable.bench
            )

            SpaceV(16)

            CardViewHome(
                title = "Rutina de Pull",
                description = "2 horas - 9 ejercicios de espalda y bícep",
                image = R.drawable.pullup
            )

            SpaceV(16)

            CardViewHome(
                title = "Rutina de Cardio",
                description = "1 hora - Ejercicios y deportes con enfoque al cardio",
                image = R.drawable.cardio
            )

            SpaceV(16)

            Button(
                onClick = onNavigateToHome,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Volver al Inicio",
                    color = TextDM,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoutineViewPreview() {
    ProyectoFinalAricHBTheme {
        RoutineView()
    }
}