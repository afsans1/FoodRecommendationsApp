package com.example.foodrecommendationapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun LogoSection(modifier :Modifier = Modifier) {
    val icon = R.drawable.the_best_part_icon
    Box(modifier
        .border(21.dp, Color.White, RectangleShape)
        .height(150.dp)){
        Row(
            modifier = Modifier
                .background(Color.LightGray),
            verticalAlignment = Alignment.CenterVertically,
            ) {
            Image(
                painter = painterResource(icon),
                contentDescription = R.drawable.the_best_part_icon.toString(),
                modifier
                    .width(150.dp)
                    .height(150.dp)
            )
            Text(
                text = stringResource(R.string.app_description),
                modifier,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
