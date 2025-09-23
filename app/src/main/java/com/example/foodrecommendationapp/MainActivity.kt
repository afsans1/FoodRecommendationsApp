package com.example.foodrecommendationapp

import FoodViewModel
import android.R.attr.padding
import android.os.Bundle
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.foodrecommendationapp.ui.theme.FoodRecommendationAppTheme
import java.util.Arrays
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodrecommendationapp.screens.NavRoutes
import com.example.foodrecommendationapp.screens.NavRoutes.Recommendation
import com.example.foodrecommendationapp.screens.NavRoutes.ListFoods
//import com.example.foodrecommendationapp.screens.NavRoutes


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
    val navController = rememberNavController()
    val foodNamesArray = stringArrayResource(R.array.food_names)
    val foodNames =Arrays.asList(foodNamesArray)
    val factory = viewModelFactory {
        initializer { FoodViewModel(foodNames) }
    }
    val viewModel: FoodViewModel = FoodViewModel(
        factory = factory)

    NavHost(navController = navController, startDestination = NavRoutes.Recommendation.route, modifier = modifier){
        composable( NavRoutes.Recommendation.route) {
            Recommendation(navController = navController)
        }
        composable( NavRoutes.Recommendation.route) {
            List(navController = navController)
        }
    }





//    FoodList(modifier, foodNames = stringArrayResource(id = R.array.food_names))
}

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


@Composable
fun FoodNameAndImage(modifier: Modifier = Modifier, viewModel: FoodViewModel, foodNames: Array<String>) {
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
                .clickable{

                },

        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { viewModel.rerollFood(foodNames) },
        ) {
            Text(text = stringResource(R.string.reroll), fontSize = 24.sp)
        }
//        Button(
//            onClick = {
//                val webView = findViewById<WebView>(R.id.webview)
//                webView.loadUrl("https://developer.android.com")
//            },
//        ) {
//            Text(text = stringResource(R.string.findfood), fontSize = 24.sp)
//        }
//        Button(
//            onClick = { viewModel.incrementItemCount() }) ,
//        ) {
//            Text(text = stringResource(R.string.changelist), fontSize = 24.sp)
//        }
    }
}



@Composable
fun FoodList(modifier: Modifier = Modifier, foodNames: Array<String>){
    foodNames.forEach { food->
        Text(text = food)
        Spacer( modifier.padding(5.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun FoodRecommendationAppPreview() {
    FoodRecommendationAppTheme {
        FoodRecommendationApp()
    }
}