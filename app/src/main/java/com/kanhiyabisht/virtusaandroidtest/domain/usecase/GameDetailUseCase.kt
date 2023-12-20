package com.kanhiyabisht.virtusaandroidtest.domain.usecase

import androidx.lifecycle.SavedStateHandle
import com.kanhiyabisht.virtusaandroidtest.core.common.Resource
import com.kanhiyabisht.virtusaandroidtest.core.utils.Constants.GAME_ID
import com.kanhiyabisht.virtusaandroidtest.domain.model.GameDetails
import com.kanhiyabisht.virtusaandroidtest.domain.repository.FreeGameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

open class GameDetailUseCase @Inject constructor(
    private val repository: FreeGameRepository,
    private val mSavedStateHandle: SavedStateHandle
) {
    operator fun invoke(): Flow<Resource<GameDetails>> {
        return mSavedStateHandle.get<String>(GAME_ID)
            ?.let { gameId ->
                repository.getGameDetails(gameId)
            } ?: flowOf(Resource.Error("Invalid Game ID"))
    }
}