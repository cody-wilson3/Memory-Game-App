package com.example.assignment2.ui.theme.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

@Composable
fun TopColor(colorString: String) {
    if ("B" == colorString) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(100.dp)
                .padding(horizontal = 25.dp)
                .background(Color.Blue)
        )
    }
    else if (colorString == "R"){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(100.dp)
                .padding(horizontal = 25.dp)
                .background(Color.Red)
        )
    }
    else if (colorString == "G"){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(100.dp)
                .padding(horizontal = 25.dp)
                .background(Color.Green)
        )
    }
    else if (colorString == "Y"){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(100.dp)
                .padding(horizontal = 25.dp)
                .background(Color.Yellow)
        )
    }
}