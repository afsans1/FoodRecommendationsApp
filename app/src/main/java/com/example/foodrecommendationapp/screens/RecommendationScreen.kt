package com.example.foodrecommendationapp.screens

import FoodViewModel
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
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
        Column (modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
            LogoSection()
            Spacer(modifier = Modifier.height(50.dp))
            Column(
                modifier = modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.shouldGet) + ": " + viewModel.currentFood,
                    fontWeight = FontWeight.Bold
                )
                Image(
                    painter = painterResource(id = viewModel.currentImage),
                    contentDescription = viewModel.currentFood,
                    modifier
                        .width(150.dp)
                        .height(150.dp)
                        .clickable {
// The intents were coded based on Carlton Davis' example code and the android studio docs
//https://gitlab.com/crdavis/intentsexamplecode/-/blob/master/app/src/main/java/com/example/intentsexamplecode/MainActivity.kt?ref_type=heads
//: https://developer.android.com/guide/components/intentscommon
//https://developer.android.com/guide/components/google-maps-intents

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
                    modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.reroll), fontSize = 24.sp)
                }
                Button(
                    onClick = {
                        val webIntent = Intent(
                        Intent.ACTION_VIEW,
                        "https://www.google.com/search?q=${viewModel.currentFood}&geo:0,0?".toUri())
                        context.startActivity(webIntent) },
                    modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.findfood), fontSize = 24.sp)
                }
                Button(
                    onClick = { navController.navigate(NavRoutes.ListFood.route) },
                    modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.changelist), fontSize = 24.sp)
                }
            }
        }
    }
}