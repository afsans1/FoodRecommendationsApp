package com.example.foodrecommendationapp

import androidx.compose.runtime.MutableState


//this class defines a MenuItem
//It contains a String property that represnts the food's name
//and a Int property that stores the address of that food's image
data class MenuItem (
    var food_name: String,
    var food_image: String,
)