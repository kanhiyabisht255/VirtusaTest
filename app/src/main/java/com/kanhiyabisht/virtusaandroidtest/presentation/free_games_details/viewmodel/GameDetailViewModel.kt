package com.kanhiyabisht.virtusaandroidtest.presentation.free_games_details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanhiyabisht.virtusaandroidtest.core.common.Resource
import com.kanhiyabisht.virtusaandroidtest.domain.usecase.GameDetailUseCase
import com.kanhiyabisht.virtusaandroidtest.presentation.free_games.states.UiEffect
import com.kanhiyabisht.virtusaandroidtest.presentation.free_games_details.states.GameDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val mGameDetailUseCase: GameDetailUseCase
) : ViewModel() {

    private val _gameDetailState = MutableStateFlow(GameDetailState())

    val gameDetailState: StateFlow<GameDetailState>
        get() = _gameDetailState

    private val _uiEffect = MutableSharedFlow<UiEffect>()

    val uiEffect: SharedFlow<UiEffect>
        get() = _uiEffect.asSharedFlow()

    init {
        getGameDetails()
    }

    private fun getGameDetails() {
        mGameDetailUseCase().onEach {
            when (it) {
                is Resource.Error -> {
                    _gameDetailState.value = GameDetailState().copy(errorMsg = it.msg)
                    _uiEffect.emit(UiEffect.ShowSnackBar(it.msg.toString()))
                }

                is Resource.Loading -> {
                    _gameDetailState.value = GameDetailState().copy(isLoading = true)
                }

                is Resource.Success -> {
                    _gameDetailState.value = GameDetailState().copy(gameDetails = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

}