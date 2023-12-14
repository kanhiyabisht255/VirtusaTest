package com.kanhiyabisht.virtusaandroidtest.presentation.free_games.states

import com.kanhiyabisht.virtusaandroidtest.domain.model.FreeGames

data class FreeGameState(
    val freeGames: List<FreeGames>? = emptyList(),
    val errorMsg: String? = "",
    val isLoading: Boolean = false
)