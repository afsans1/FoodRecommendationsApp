package com.example.foodrecommendationapp.screens

import FoodViewModel
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.foodrecommendationapp.LogoSection
import com.example.foodrecommendationapp.R

class ListScreen {
    @SuppressLint("NotConstructor")
    @Composable
    fun ListFood(navController: NavController, modifier: Modifier, viewModel: FoodViewModel) {
        var addedFood by remember { mutableStateOf("") }
        //This code is from the Week-4-Jetpack-Compose-Navigation-And-Intents powerpoint slide 40
        val onFoodChange = { text: String -> addedFood = text }
        Column(modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally){
            LogoSection()
            Spacer(modifier = Modifier.height(50.dp))
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(modifier = modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    //This code is from the Week-4-Jetpack-Compose-Navigation-And-Intents powerpoint slide 42
                    CustomTextField(
                        //change the title to be in resources
                        title = stringResource(R.string.textFieldTitle), textState = addedFood,
                        onTextChange = onFoodChange
                    )

                    Spacer(modifier = Modifier.size(30.dp))
                    Button(onClick =
                        {viewModel.updateFoodList(addedFood)},
                        modifier.fillMaxWidth())
                    {
                        Text(text =stringResource(R.string.additem))
                    }
                    //creating a button for all the menu items in the foodItems list
                    viewModel.foodItems.forEach { food ->
                        Button(onClick = {
                            viewModel.removeButton(food)},
                            modifier.fillMaxWidth())
                        {
                            Text(text = food.food_name)
                        }
                    }
                    Button(onClick = {
                        //I call the rerollFood method again to not see a menu item
                        //that was deleted in the list on the home page
                        viewModel.rerollFood()
                        navController.navigate(NavRoutes.Recommendation.route)
                    },modifier.fillMaxWidth())
                    {
                        Text(text = stringResource(R.string.savechanges))
                    }

                }
            }
        }
    }

//This code is from the Week-4-Jetpack-Compose-Navigation-And-Intents powerpoint slide 40
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