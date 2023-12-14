package com.kanhiyabisht.virtusaandroidtest.presentation.navigation.screen

sealed class Screen(val route: String) {
    object GameScreen : Screen("game_screen")
}