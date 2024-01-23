package com.example.assignment2.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.assignment2.ui.theme.components.TopColor
import com.example.assignment2.ui.theme.navigation.RootNavigation
import com.example.assignment2.ui.theme.navigation.Routes
import com.example.assignment2.ui.theme.viewmodels.PlayGameViewModel
import kotlin.random.Random

@Composable
fun PlayGameScreen(navController: NavController) {

    val viewModel: PlayGameViewModel = viewModel()
    val viewModelState = viewModel.scoreState
    LaunchedEffect(true) {
        viewModel.loadScores()
    }

    //        function returns random color string
    fun getRandomColor(): String {
        val rand = Random.nextInt(1, 5)
        if (rand == 1) {
            return "B"
        } else if (rand == 2) {
            return "R"
        } else if (rand == 3) {
            return "G"
        } else {
            return "Y"
        }
    }

    //function checks whether two mutableLists have equal contents
    fun equalsList(
        correctList: MutableList<String>,
        currentList: MutableList<String>
    ): Boolean {
        if (currentList.size < correctList.size) {
            return false
        }
        for (i in 0 until correctList.size) {
            if (correctList[i] != currentList[i]) {
                return false
            }
        }
        return true
    }

    //initializing variables
    var topColor = remember { mutableStateOf(getRandomColor()) }
    var level by remember { mutableStateOf(1) }
    var correctSequence = remember { mutableStateListOf<String>(topColor.value) }
    var currentSequence = mutableListOf<String>()
    var playing by remember { mutableStateOf(true) }


    //function is called everytime a color square is clicked
    fun performChecks() {
        var sequencesMatch =
            equalsList(correctSequence.subList(0, currentSequence.size), currentSequence)
        if (equalsList(correctSequence, currentSequence)) {
            var newColor = getRandomColor()
            topColor.value = newColor
            correctSequence.add(newColor)
            level++
            currentSequence.clear()
        } else if (sequencesMatch) {
            return
        } else {
            playing = false
        }
    }


    // Top Container

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .size(180.dp)
    ) {


        // Functional Container for Level Number
        Box(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 25.dp)
        )
        {
            Text(
                text = "Level $level",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
            )
        }

        // Color Box at very top
        TopColor(topColor.value)

    }

    // Large Square container for color choices
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 240.dp, horizontal = 25.dp)
            .background(Color.White)
            .clip(RoundedCornerShape(40.dp))
    ) {


        // Top row of colors
        Row(
            modifier = Modifier
                .weight(1f)
        )
        {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(Color.Blue)
                    .clickable(
                        onClick = {
                            currentSequence.add("B")
                            performChecks()
                        }
                    )
            ) {}
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(Color.Red)
                    .clickable(
                        onClick = {
                            currentSequence.add("R")
                            performChecks()
                        }
                    )
            ) {}
        }
        //Bottom Row of colors
        Row(
            modifier = Modifier
                .weight(1f)
        )
        {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(Color.Green)
                    .clickable(
                        onClick = {
                            currentSequence.add("G")
                            performChecks()
                        }
                    )
            ) {}
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(Color.Yellow)
                    .clickable(
                        onClick = {
                            currentSequence.add("Y")
                            performChecks()
                        }
                    )
            ) {}
        }
    }
    // is only called when a player loses
    if (!playing) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(60.dp)
                .padding(horizontal = 25.dp)
                .background(Color.Red)
                .border(3.dp, Color.Gray)
                .clickable(
                    onClick = {
                        playing = true
                        level = 1
                        correctSequence.clear()
                        currentSequence.clear()
                        var newColor = getRandomColor()
                        topColor.value = newColor
                        correctSequence.add(newColor)
                        navController.navigate(Routes.home.route)

                    }
                )
        ) {
            if (level > viewModelState.previousHighScore) {
                viewModel.setNewHighScore("previousHighScore", level)
            }
            Text(
                text = "GAME OVER! You made it to Level $level! \n Click to return to Home Screen",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}