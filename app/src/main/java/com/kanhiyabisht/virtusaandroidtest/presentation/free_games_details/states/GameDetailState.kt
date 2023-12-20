package com.kanhiyabisht.virtusaandroidtest.presentation.free_games_details.states

import com.kanhiyabisht.virtusaandroidtest.domain.model.GameDetails

data class GameDetailState(
    val gameDetails: GameDetails? = null,
    val errorMsg: String? = "",
    val isLoading: Boolean = false
)