package com.example.foodrecommendationapp

import FoodViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodrecommendationapp.screens.ListScreen
import com.example.foodrecommendationapp.screens.NavRoutes.ListFood
import com.example.foodrecommendationapp.screens.NavRoutes.Recommendation
import com.example.foodrecommendationapp.screens.RecommendationScreen
import com.example.foodrecommendationapp.ui.theme.FoodRecommendationAppTheme
import androidx.compose.ui.platform.LocalContext


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodRecommendationAppTheme {
                FoodRecommendationApp()
            }
        }
    }
}

@Composable
fun FoodRecommendationApp(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val navController = rememberNavController()
    val foodNamesArray = stringArrayResource(R.array.food_names).toList()
//https://stackoverflow.com/questions/75227494/android-getresources-getidentifier-replacement
    val foodImagesArray = stringArrayResource(R.array.food_images).map { imageName ->
        context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }.toList()

    val factory = viewModelFactory {
        initializer { FoodViewModel(foodNamesArray,foodImagesArray) }
    }
    val viewModel: FoodViewModel = viewModel(factory = factory)
    val recommendationScreen = RecommendationScreen()
    val listScreen = ListScreen()
    NavHost(navController = navController,
            startDestination = Recommendation.route,
            modifier = modifier){
        composable( Recommendation.route) {

            recommendationScreen.Recommendation(navController = navController, modifier = modifier,viewModel = viewModel, context = context)
        }
        composable(ListFood.route) {
            listScreen.ListFood(navController = navController, modifier = modifier,viewModel = viewModel)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FoodRecommendationAppPreview() {
    FoodRecommendationAppTheme {
        FoodRecommendationApp()
    }
}