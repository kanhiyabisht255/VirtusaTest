package com.kanhiyabisht.virtusaandroidtest.data.remote.mapper

import com.kanhiyabisht.virtusaandroidtest.data.remote.dto.FreeGamesDto
import com.kanhiyabisht.virtusaandroidtest.data.remote.dto.GameDetailDTO
import com.kanhiyabisht.virtusaandroidtest.domain.model.FreeGames
import com.kanhiyabisht.virtusaandroidtest.domain.model.GameDetails

fun FreeGamesDto.toDomainFreeGames(): FreeGames {
    return FreeGames(
        gameUrl = gameUrl,
        id = id,
        shortDescription = shortDescription,
        thumbnail = thumbnail,
        title = title
    )
}

fun GameDetailDTO.toDomainGameDetails(): GameDetails {
    return GameDetails(
        thumbnail = thumbnail,
        description = description
    )
}