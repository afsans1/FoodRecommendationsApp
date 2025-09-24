package com.example.foodrecommendationapp.screens

//This defines the routes for the app
sealed class NavRoutes (val route: String){
    object Recommendation : NavRoutes("Recommendation")
    object ListFood : NavRoutes("ListFood")

}