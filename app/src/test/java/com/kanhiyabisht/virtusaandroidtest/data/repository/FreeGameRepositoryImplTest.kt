package com.kanhiyabisht.virtusaandroidtest.data.repository

import com.kanhiyabisht.virtusaandroidtest.core.common.Resource
import com.kanhiyabisht.virtusaandroidtest.data.remote.FreeGameApi
import com.kanhiyabisht.virtusaandroidtest.data.remote.dto.FreeGamesDto
import com.kanhiyabisht.virtusaandroidtest.domain.repository.FreeGameRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FreeGameRepositoryImplTest {

    @Mock
    lateinit var freeGameApi: FreeGameApi

    private lateinit var repository: FreeGameRepository

    @Before
    fun setup() {
        repository = FreeGameRepositoryImpl(freeGameApi)
    }

    @Test
    fun `getFreeGames should return Success with mapped data`() = runBlocking {
        val fakeFreeGamesDtoList = listOf(
            FreeGamesDto(
                developer = "Developer",
                freeToGameProfileUrl = "profileUrl",
                gameUrl = "gameUrl",
                genre = "Genre",
                id = 1,
                platform = "Platform",
                publisher = "Publisher",
                releaseDate = "Release Date",
                shortDescription = "Short Description",
                thumbnail = "Thumbnail",
                title = "Title"
            )
        )

        Mockito.`when`(freeGameApi.getFreeGame()).thenReturn(fakeFreeGamesDtoList)
        val result = repository.getFreeGames().drop(1).first()
        assertTrue(result is Resource.Success)
    }

    @Test
    fun `getFreeGames should return Success with empty list`() = runBlocking {
        Mockito.`when`(freeGameApi.getFreeGame()).thenReturn(emptyList())
        val result = repository.getFreeGames().drop(1).first()
        assertTrue(result is Resource.Success)
    }

    @Test
    fun `getFreeGames should return Error`() = runBlocking {
        val errorMessage = "Failed to fetch free games"
        Mockito.`when`(freeGameApi.getFreeGame()).thenThrow(RuntimeException(errorMessage))
        val result = repository.getFreeGames().drop(1).first()
        assertTrue(result is Resource.Error)
        assertEquals("Error message does not match", errorMessage, (result as Resource.Error).msg)
    }

    @Test
    fun `getGameDetails should return Error`() = runBlocking {
        val gameId = "456"
        val errorMessage = "Failed to fetch game details"
        Mockito.`when`(freeGameApi.getGamesDetails(gameId))
            .thenThrow(RuntimeException(errorMessage))
        val result = repository.getGameDetails(gameId).drop(1).first()
        assertTrue(result is Resource.Error)
        assertEquals("Error message does not match", errorMessage, (result as Resource.Error).msg)
    }
}


