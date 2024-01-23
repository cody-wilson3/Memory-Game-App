package com.example.assignment2.ui.theme.viewmodels

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel


class HighScorelistState() {
    // This might cause trouble down the line because ditton had it as mutableStateOf(0)
    var previousHighScore = 0
    var loading by mutableStateOf(true)
}

class PlayGameViewModel(application: Application) : AndroidViewModel(application) {
    val scoreState = HighScorelistState()
    private val sharedPreferences: SharedPreferences
    init {
        sharedPreferences = application.getSharedPreferences("default", Context.MODE_PRIVATE)
    }

    fun loadScores() {
        scoreState.previousHighScore = sharedPreferences.getInt("previousHighScore", 0)
        scoreState.loading = false
    }

    fun setNewHighScore(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
        if (key == "previousHighScore") { scoreState.previousHighScore = value }
    }
}