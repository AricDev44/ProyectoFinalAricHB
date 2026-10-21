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
fun PremiumView(
    modifier: Modifier = Modifier,
    onNavigateToRoutines: () -> Unit = {},
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
                        Title(text = "Premium")
                    }
                )
            }
        }
    ) { paddingValues ->
        ContentPremiumView(
            modifier = Modifier.padding(paddingValues),
            onNavigateToRoutines = onNavigateToRoutines,
            onNavigateToHome = onNavigateToHome
        )
    }
}

@Composable
fun ContentPremiumView(
    modifier: Modifier = Modifier,
    onNavigateToRoutines: () -> Unit = {},
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
                text = "Sección Premium",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = TextDM
            )

            SpaceV(24)

            CardViewHome(
                title = "Rutinas de ejercicio personalizadas",
                description = "Accede a rutinas personalizadas que vayan de acuerdo con tus metas",
                image = R.drawable.gym
            )

            SpaceV(16)

            CardViewHome(
                title = "Dietas personalizadas con detalle",
                description = "Obtén dietas personalizadas para ganar masa muscular o perder grasa",
                image = R.drawable.diet
            )

            SpaceV(16)

            Button(
                onClick = onNavigateToRoutines,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Ver Rutinas",
                    color = TextDM,
                    fontWeight = FontWeight.Bold
                )
            }

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
fun PremiumViewPreview() {
    ProyectoFinalAricHBTheme {
        PremiumView()
    }
}