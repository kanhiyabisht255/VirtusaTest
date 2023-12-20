package com.kanhiyabisht.virtusaandroidtest.presentation.free_games.states

sealed class UiEffect {
    class ShowSnackBar(val msg: String) : UiEffect()
}