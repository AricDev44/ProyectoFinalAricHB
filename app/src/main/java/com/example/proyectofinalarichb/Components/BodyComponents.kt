package com.example.proyectofinalarichb.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinalarichb.ui.theme.SecondaryGreenDM
import com.example.proyectofinalarichb.ui.theme.TextDM

@Composable
fun CardViewHome(
    title: String,
    description: String,
    image: Int
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = SecondaryGreenDM
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDM,
                    lineHeight = 20.sp
                )
                SpaceV(4)
                Text(
                    text = description,
                    fontSize = 12.sp,
                    color = TextDM.copy(alpha = 0.9f),
                    lineHeight = 16.sp
                )
            }
            Image(
                painter = painterResource(id = image),
                contentDescription = title,
                modifier = Modifier
                    .size(85.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun SpaceV(space: Int) {
    Spacer(
        Modifier.height(space.dp)
    )
}

@Composable
fun SpaceH(space: Int) {
    Spacer(
        Modifier.width(space.dp)
    )
}