package com.example.foodrecommendationapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

//This is the upper part of the app that is present on all screens
@Composable
fun LogoSection(modifier :Modifier = Modifier) {
    val icon = R.drawable.the_best_part_icon
    Image(
        painter = painterResource(icon),
        contentDescription = R.drawable.the_best_part_icon.toString(),
        modifier.width(250.dp).height(250.dp).padding(10.dp))
}
