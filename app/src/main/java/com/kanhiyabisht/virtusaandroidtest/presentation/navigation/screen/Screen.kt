package com.kanhiyabisht.virtusaandroidtest.presentation.navigation.screen

import com.kanhiyabisht.virtusaandroidtest.core.utils.Constants.GAME_DETAILS_SCREEN
import com.kanhiyabisht.virtusaandroidtest.core.utils.Constants.GAME_SCREEN

sealed class Screen(val route: String) {
    object GameScreen : Screen(route = GAME_SCREEN)
    object GameDetailsScreen : Screen(route = GAME_DETAILS_SCREEN) {
        fun getGameDetailsRoute(gameId: String): String {
            return "$route/$gameId"
        }
    }

}