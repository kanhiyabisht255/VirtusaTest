package com.kanhiyabisht.virtusaandroidtest.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kanhiyabisht.virtusaandroidtest.core.utils.Constants.GAME_DETAILS_SCREEN
import com.kanhiyabisht.virtusaandroidtest.core.utils.Constants.GAME_ID
import com.kanhiyabisht.virtusaandroidtest.presentation.free_games.components.GameScreen
import com.kanhiyabisht.virtusaandroidtest.presentation.free_games.states.UiEffect
import com.kanhiyabisht.virtusaandroidtest.presentation.free_games.viewmodel.FreeGamesViewModel
import com.kanhiyabisht.virtusaandroidtest.presentation.free_games_details.viewmodel.GameDetailViewModel
import com.kanhiyabisht.virtusaandroidtest.presentation.free_games_details.components.GameDetailScreen
import com.kanhiyabisht.virtusaandroidtest.presentation.navigation.screen.Screen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(navController: NavHostController) {

    val snackBarHostState = remember {
        SnackbarHostState()
    }

    Scaffold(snackbarHost = { SnackbarHost(hostState = snackBarHostState) }) {
        NavHost(
            navController = navController,
            startDestination = Screen.GameScreen.route,
            modifier = Modifier.padding(it)
        ) {

            composable(route = Screen.GameScreen.route) {
                val freeGameViewModel = hiltViewModel<FreeGamesViewModel>()
                val state = freeGameViewModel.freeGameState.collectAsStateWithLifecycle()

                LaunchedEffect(key1 = true) {
                    freeGameViewModel.uiEffect.collectLatest { uiEffect ->
                        when (uiEffect) {
                            is UiEffect.ShowSnackBar -> {
                                launch {
                                    snackBarHostState.showSnackbar(
                                        uiEffect.msg,
                                        duration = SnackbarDuration.Long
                                    )
                                }
                            }
                        }
                    }
                }

                GameScreen(freeGameState = state.value, modifier = Modifier) { gameId ->
                    navController.navigate(Screen.GameDetailsScreen.getGameDetailsRoute(gameId))
                }
            }

            composable(
                route = "$GAME_DETAILS_SCREEN/{$GAME_ID}"
            ) { backStackEntry ->
                val gameId = backStackEntry.arguments?.getString(GAME_ID)

                val gameDetailViewModel = hiltViewModel<GameDetailViewModel>()
                val state = gameDetailViewModel.gameDetailState.collectAsStateWithLifecycle()

                LaunchedEffect(key1 = true) {
                    gameDetailViewModel.uiEffect.collectLatest { uiEffect ->
                        when (uiEffect) {
                            is UiEffect.ShowSnackBar -> {
                                launch {
                                    snackBarHostState.showSnackbar(
                                        uiEffect.msg,
                                        duration = SnackbarDuration.Long
                                    )
                                }
                            }
                        }
                    }
                }
                if (gameId != null) {
                    GameDetailScreen(gameDetailState = state.value, modifier = Modifier)
                }
            }
        }
    }
}