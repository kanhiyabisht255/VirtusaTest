package com.kanhiyabisht.virtusaandroidtest.domain.repository

import com.kanhiyabisht.virtusaandroidtest.core.common.Resource
import com.kanhiyabisht.virtusaandroidtest.domain.model.FreeGames
import com.kanhiyabisht.virtusaandroidtest.domain.model.GameDetails
import kotlinx.coroutines.flow.Flow

interface FreeGameRepository {

    fun getFreeGames(): Flow<Resource<List<FreeGames>>>
    fun getGameDetails(gameId : String): Flow<Resource<GameDetails>>
}