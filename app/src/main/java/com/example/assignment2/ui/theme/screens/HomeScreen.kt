package com.example.assignment2.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.LaunchedEffect
import com.example.assignment2.ui.theme.navigation.Routes
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.assignment2.ui.theme.viewmodels.HomeScreenViewModel


@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeScreenViewModel = viewModel()
    val viewModelState = viewModel.homeState
    LaunchedEffect(true) {
        viewModel.loadScores()
    }

    // Container for UI Composables
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Current High Score: ${viewModelState.previousHighScore}",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(50.dp)
                    .padding(horizontal = 25.dp)
                    .background(Color.Green)
                    .clickable {
                        navController.navigate(Routes.playGame.route)
                    }
            ) {
                Text(
                    text = "Play Game",
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,

                )
            }
        }
    }
}



