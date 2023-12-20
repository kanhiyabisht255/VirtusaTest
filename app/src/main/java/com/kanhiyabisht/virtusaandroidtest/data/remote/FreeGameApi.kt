package com.kanhiyabisht.virtusaandroidtest.data.remote

import com.kanhiyabisht.virtusaandroidtest.data.remote.dto.FreeGamesDto
import com.kanhiyabisht.virtusaandroidtest.data.remote.dto.GameDetailDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface FreeGameApi {

    @GET("games")
    suspend fun getFreeGame() : List<FreeGamesDto>

    @GET("game")
    suspend fun getGamesDetails(@Query("id") gameId: String): GameDetailDTO

}