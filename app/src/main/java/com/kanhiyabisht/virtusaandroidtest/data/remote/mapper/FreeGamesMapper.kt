package com.kanhiyabisht.virtusaandroidtest.data.remote.mapper

import com.kanhiyabisht.virtusaandroidtest.data.remote.dto.FreeGamesDto
import com.kanhiyabisht.virtusaandroidtest.domain.model.FreeGames

fun FreeGamesDto.toDomainFreeGames(): FreeGames {
    return FreeGames(
        gameUrl = gameUrl,
        id = id,
        shortDescription = shortDescription,
        thumbnail = thumbnail,
        title = title
    )
}