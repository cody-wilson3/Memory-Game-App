package com.example.assignment2.ui.theme.viewmodels

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel

class HomeScreenState {
    var previousHighScore = 0
    var loading by mutableStateOf(true)
}

class HomeScreenViewModel(application: Application): AndroidViewModel(application){
    val homeState = HomeScreenState()
    private val sharedPreferences: SharedPreferences
    init {
        sharedPreferences = application.getSharedPreferences("default", Context.MODE_PRIVATE)
    }

    fun loadScores() {
        homeState.previousHighScore = sharedPreferences.getInt("previousHighScore", 0)
        homeState.loading = false
    }
}