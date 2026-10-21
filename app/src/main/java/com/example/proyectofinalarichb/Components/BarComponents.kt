package com.example.proyectofinalarichb.Components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.proyectofinalarichb.ui.theme.KeyOrangeDM
import com.example.proyectofinalarichb.ui.theme.TextDM

@Composable
fun Title(text: String){
    Text(text = text, fontSize = 25.sp, color = TextDM, fontWeight = FontWeight.Bold)
}

@Composable
fun PremiumButton(onClick: () -> Unit = {}){
    FloatingActionButton(
        onClick = onClick,
        containerColor = KeyOrangeDM
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Botón de funciones premium",
            tint = TextDM
        )
    }
}

@Composable
fun MainScaffold(
    modifier: Modifier = Modifier,
    selectedItem: Int,
    onItemSelected: (Int) -> Unit,
    topBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    showBottomBar: Boolean = true,
    content: @Composable (PaddingValues) -> Unit
){
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        topBar = topBar,
        bottomBar = {
            if (showBottomBar){
                BottomMenu(
                    selectedItem = selectedItem,
                    onItemSelected = onItemSelected
                )
            }
        },
        floatingActionButton = floatingActionButton,
        content = content
    )
}