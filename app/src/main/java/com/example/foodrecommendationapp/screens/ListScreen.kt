package com.example.foodrecommendationapp.screens

import FoodViewModel
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.foodrecommendationapp.LogoSection
import com.example.foodrecommendationapp.MenuItem
import com.example.foodrecommendationapp.R

class ListScreen {
    @SuppressLint("NotConstructor")
    @Composable
    fun ListFood(navController: NavController, modifier: Modifier, viewModel: FoodViewModel) {
        var addedFood by remember { mutableStateOf("") }
        var foodItems by remember {mutableStateOf(viewModel.foodItems)}
        val onFoodChange = { text: String -> addedFood = text }
        Column{
            LogoSection()
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    CustomTextField(
                        title = "Enter a food you want to add", textState = addedFood,
                        onTextChange = onFoodChange
                    )

                    Spacer(modifier = Modifier.size(30.dp))
                    Button(onClick = {viewModel.updateFoodList(addedFood)}) {
                        Text(text =stringResource(R.string.additem))
                    }
                    viewModel.foodItems.forEach { food ->
                        Button(onClick = {}, modifier.fillMaxWidth()) {
                            Text(text = food.food_name)
                        }
                    }
                    Button(onClick = {navController.navigate(NavRoutes.Recommendation.route)}) {
                        Text(text = "Save Changes")
                    }

                }
            }
        }
    }

    @Composable
    fun CustomTextField (title: String, textState: String, onTextChange: (String) -> Unit)
    {
        OutlinedTextField (value = textState, onValueChange = { onTextChange(it) },
            singleLine = true,
            label = { Text(title) },
            modifier = Modifier.padding(10.dp),
            textStyle = TextStyle (fontWeight = FontWeight.Bold, fontSize = 30.sp)
        )
    }

}