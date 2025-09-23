package com.example.foodrecommendationapp.screens

import FoodViewModel
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.foodrecommendationapp.LogoSection
import com.example.foodrecommendationapp.R


class RecommendationScreen {
    @Composable
    fun Recommendation(
        navController: NavController,
        modifier: Modifier,
        viewModel: FoodViewModel) {
        Column {
            LogoSection()
            Column(
                modifier = modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "You should eat a ${viewModel.currentFood}",
                    fontWeight = FontWeight.Bold
                )
                Image(
                    painter = painterResource(id = viewModel.currentImage),
                    contentDescription = viewModel.currentFood,
                    modifier
                        .width(150.dp)
                        .height(150.dp)
                        .clickable {

                        },

                    )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { viewModel.rerollFood() },
                ) {
                    Text(text = stringResource(R.string.reroll), fontSize = 24.sp)
                }
                Button(
                    onClick = {
                        val webIntent = Intent(
                        Intent.ACTION_VIEW,
                        "https://gitlab.com/crdavis/intentsexamplecode".toUri())
                        context.startActivity(webIntent)

                    },
                ) {
                    Text(text = stringResource(R.string.findfood), fontSize = 24.sp)
                }
                Button(
                    onClick = { navController.navigate(NavRoutes.ListFood.route) },
                ) {
                    Text(text = stringResource(R.string.changelist), fontSize = 24.sp)
                }
            }
        }
    }
}