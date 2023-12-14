package com.kanhiyabisht.virtusaandroidtest.data.remote

import com.kanhiyabisht.virtusaandroidtest.data.remote.dto.FreeGamesDto
import retrofit2.http.GET

interface FreeGameApi {

    @GET("games")
    suspend fun getFreeGame() : List<FreeGamesDto>
}