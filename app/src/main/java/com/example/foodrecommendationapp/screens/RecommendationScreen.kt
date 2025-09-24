package com.example.foodrecommendationapp.screens

import FoodViewModel
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import com.example.foodrecommendationapp.LogoSection
import com.example.foodrecommendationapp.R


class RecommendationScreen {
    @Composable
    fun Recommendation(
        navController: NavController,
        modifier: Modifier,
        viewModel: FoodViewModel,
        context : Context) {
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
                            Intent(Intent.ACTION_MAIN).also { it.`package`="com.google.android.apps.maps"
                                try {
                                    context.startActivity(it)
                                } catch (e:ActivityNotFoundException) {
                                    e.printStackTrace()
                                }
                            }

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
                        "https://www.google.com/search?q=${viewModel.currentFood} near Montreal, QC".toUri())
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