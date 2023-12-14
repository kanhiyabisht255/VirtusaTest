package com.kanhiyabisht.virtusaandroidtest.data.repository

import android.util.Log
import com.kanhiyabisht.virtusaandroidtest.core.common.Resource
import com.kanhiyabisht.virtusaandroidtest.data.remote.FreeGameApi
import com.kanhiyabisht.virtusaandroidtest.data.remote.mapper.toDomainFreeGames
import com.kanhiyabisht.virtusaandroidtest.domain.model.FreeGames
import com.kanhiyabisht.virtusaandroidtest.domain.repository.FreeGameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FreeGameRepositoryImpl @Inject constructor(private val  freeGameApi: FreeGameApi) : FreeGameRepository {

    override fun getFreeGames(): Flow<Resource<List<FreeGames>>> = flow {
        emit(Resource.Loading())
        val result = freeGameApi.getFreeGame().map {
            it.toDomainFreeGames()
        }
        emit(Resource.Success(result))
    }.flowOn(Dispatchers.IO).catch {
        emit(Resource.Error(it.message.toString()))
    }
}