package com.example.assignment2.ui.theme.navigation

import androidx.navigation.NavHostController
import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost



import com.example.assignment2.ui.theme.screens.HomeScreen
import com.example.assignment2.ui.theme.screens.PlayGameScreen

@Composable
fun RootNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.home.route) {
        composable(Routes.home.route) { HomeScreen(navController = navController)}
        composable(Routes.playGame.route) { PlayGameScreen(navController = navController)}
    }
}