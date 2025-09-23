package com.example.navigationexamplecode.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

import com.example.foodrecommendationapp.screens.NavRoutes

class ListScreen {
    @Composable
    fun Welcome (navController: NavHostController, userName: String?) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
        {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Welcome, $userName", style = MaterialTheme.typography.headlineSmall)

                Spacer(modifier = Modifier.size(30.dp))

                Button(onClick = { }) {

                    Text(text = "Setup up your Profile")
                }
            }
        }
    }

}