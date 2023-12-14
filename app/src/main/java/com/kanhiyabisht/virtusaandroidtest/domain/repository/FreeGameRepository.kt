package com.kanhiyabisht.virtusaandroidtest.domain.repository

import com.kanhiyabisht.virtusaandroidtest.core.common.Resource
import com.kanhiyabisht.virtusaandroidtest.domain.model.FreeGames
import kotlinx.coroutines.flow.Flow

interface FreeGameRepository {

    fun getFreeGames(): Flow<Resource<List<FreeGames>>>
}