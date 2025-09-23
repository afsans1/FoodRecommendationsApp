package com.example.foodrecommendationapp.screens

sealed class NavRoutes (val route: String){
    object Recommendation : NavRoutes("Recommendation")
    object ListFoods : NavRoutes("ListFoods")

}