package com.example.proyectofinalarichb.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinalarichb.Data.Model.Dish
import com.example.proyectofinalarichb.Data.Model.TrackedDish
import com.example.proyectofinalarichb.R
import com.example.proyectofinalarichb.ui.theme.KeyOrangeDM
import com.example.proyectofinalarichb.ui.theme.SecondaryGreenDM
import com.example.proyectofinalarichb.ui.theme.TextDM

fun getDishImageResource(imageResId: Int, name: String): Int {
    return when {
        name.contains("Ceviche", ignoreCase = true) -> R.drawable.ceviche
        name.contains("Chilaquiles", ignoreCase = true) -> R.drawable.chilaquiles
        name.contains("Huarache", ignoreCase = true) -> R.drawable.huabistec
        name.contains("lentejas", ignoreCase = true) -> R.drawable.lentejas
        name.contains("Nopales", ignoreCase = true) -> R.drawable.nopales
        name.contains("Quesadillas", ignoreCase = true) -> R.drawable.quesadillas
        name.contains("Tacos", ignoreCase = true) -> R.drawable.tacospollo
        else -> imageResId
    }
}

@Composable
fun DishCardHome(
    dish: Dish,
    onAddClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .width(170.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = SecondaryGreenDM
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = getDishImageResource(dish.image, dish.name)),
                contentDescription = dish.name,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            SpaceV(8)

            Text(
                text = dish.name,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = TextDM,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.align(Alignment.Start)
            )

            Text(
                text = "${dish.portionQuantity} ${dish.portionUnit} • ${dish.calories} kcal",
                fontSize = 12.sp,
                color = TextDM,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Start)
            )

            SpaceV(4)

            Text(
                text = "P: ${dish.protein}g | C: ${dish.carbs}g | G: ${dish.fat}g",
                fontSize = 11.sp,
                color = TextDM.copy(alpha = 0.8f),
                modifier = Modifier.align(Alignment.Start)
            )

            SpaceV(8)

            Button(
                onClick = onAddClick,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = KeyOrangeDM
                )
            ) {
                Text("Agregar", color = TextDM, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun DishCardRow(
    dish: Dish,
    isAdded: Boolean = false,
    onAddClick: () -> Unit = {},
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = SecondaryGreenDM
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = getDishImageResource(dish.image, dish.name)),
                contentDescription = dish.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            SpaceH(12)

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = dish.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = TextDM,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                SpaceV(4)

                Text(
                    text = "Porción: ${dish.portionQuantity} ${dish.portionUnit}",
                    fontSize = 13.sp,
                    color = TextDM
                )

                Text(
                    text = "${dish.calories} kcal",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDM
                )

                SpaceV(2)

                Text(
                    text = "P: ${dish.protein}g  C: ${dish.carbs}g  G: ${dish.fat}g",
                    fontSize = 11.sp,
                    color = TextDM.copy(alpha = 0.85f)
                )
            }

            SpaceH(8)

            Button(
                onClick = onAddClick,
                enabled = !isAdded,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = KeyOrangeDM,
                    disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    disabledContentColor = TextDM.copy(alpha = 0.6f)
                )
            ) {
                Text(
                    text = if (isAdded) "Agregado" else "Agregar",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDM
                )
            }
        }
    }
}

@Composable
fun TrackedDishCard(
    trackedDish: TrackedDish,
    onRemoveClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = SecondaryGreenDM
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = getDishImageResource(trackedDish.image, trackedDish.name)),
                contentDescription = trackedDish.name,
                modifier = Modifier
                    .size(65.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            SpaceH(12)

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = trackedDish.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = TextDM,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                SpaceV(2)

                Text(
                    text = "${trackedDish.calories} kcal",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDM
                )

                SpaceV(2)

                Text(
                    text = "P: ${trackedDish.protein}g  C: ${trackedDish.carbs}g  G: ${trackedDish.fat}g",
                    fontSize = 11.sp,
                    color = TextDM.copy(alpha = 0.85f)
                )
            }

            IconButton(onClick = onRemoveClick) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar de mi día",
                    tint = Color.Red
                )
            }
        }
    }
}