package com.example.proyectofinalarichb.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectofinalarichb.Components.CardViewHome
import com.example.proyectofinalarichb.Components.MainScaffold
import com.example.proyectofinalarichb.Components.PremiumButton
import com.example.proyectofinalarichb.Components.SpaceV
import com.example.proyectofinalarichb.Components.Title
import com.example.proyectofinalarichb.R
import com.example.proyectofinalarichb.ViewModel.AuthViewModel
import com.example.proyectofinalarichb.ui.theme.KeyOrangeDM
import com.example.proyectofinalarichb.ui.theme.ProyectoFinalAricHBTheme
import com.example.proyectofinalarichb.ui.theme.TextDM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView (
    modifier: Modifier = Modifier,
    onItemSelected: (Int) -> Unit,
    onLogout: () -> Unit = {},
    onPremiumButtonClick: () -> Unit = {}
){
    MainScaffold(
        modifier = modifier,
        selectedItem = 0,
        onItemSelected = onItemSelected,
        topBar = {
           Column {
               CenterAlignedTopAppBar(
                   title = {
                       Title(text = "MexiFit")
                   }
               )
           }
        },
        floatingActionButton = {
            PremiumButton(onClick = onPremiumButtonClick)
        }
    ) { paddingValues ->
        ContentHomeView(
            modifier = Modifier.padding(paddingValues),
            onLogout = onLogout
        )
    }
}

@Composable
fun ContentHomeView (
    modifier: Modifier = Modifier,
    onLogout: () -> Unit = {},
    authVM: AuthViewModel? = if (LocalInspectionMode.current) null else viewModel()
){
    val context = LocalContext.current

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            CardViewHome(
                title = "Bienvenido a MexiFit",
                description = "Tu dieta con los platillos de siempre",
                image = R.drawable.logo
            )

            SpaceV(16)

            CardViewHome(
                title = "Lista de Alimentos Sanos",
                description = "Contamos con una base de datos con una variedad extensa de platillos con ingredientes cotidianos",
                image = R.drawable.lista
            )

            SpaceV(16)

            CardViewHome(
                title = "Calculadora de calorías y macros",
                description = "Obtén tu cantidad de calorías consumidas en el día, así como de tus macros",
                image = R.drawable.calculadora
            )

            SpaceV(16)

            CardViewHome(
                title = "Mapa con opciones de restaurantes fit",
                description = "Visualiza restaurantes cerca de tu zona con alternativas de comida saludables",
                image = R.drawable.mapaicono
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (authVM != null) {
                        authVM.logout(onLogout = onLogout)
                    } else {
                        onLogout()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = KeyOrangeDM
                )
            ){
                Text(
                    color = TextDM,
                    text = "Cerrar sesión",
                    fontWeight = FontWeight.Bold
                )
            }

            SpaceV(16)
        }
    }
}

@Composable
fun SectionHeader(title: String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = title,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            color = TextDM
        )
        Text(
            text = "Ver todas",
            textAlign = TextAlign.End,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeViewPreview(){
    ProyectoFinalAricHBTheme {
        HomeView(
            modifier = Modifier,
            onItemSelected = { }
        )
    }
}