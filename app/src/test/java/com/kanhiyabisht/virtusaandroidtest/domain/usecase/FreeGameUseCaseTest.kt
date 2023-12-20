package com.kanhiyabisht.virtusaandroidtest.domain.usecase

import com.kanhiyabisht.virtusaandroidtest.core.common.Resource
import com.kanhiyabisht.virtusaandroidtest.domain.model.FreeGames
import com.kanhiyabisht.virtusaandroidtest.domain.repository.FreeGameRepository
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FreeGameUseCaseTest {

    private val mockRepository: FreeGameRepository = mockk()
    private lateinit var useCase: FreeGameUseCase

    @Before
    fun setup() {
        useCase = FreeGameUseCase(mockRepository)
    }

    @Test
    fun `invoke should return Success with mapped data from repository`() = runBlocking {
        // Arrange
        val fakeFreeGames = listOf(
            FreeGames("gameUrl1", 1, "Short Description1", "Thumbnail1", "Title1"),
            FreeGames("gameUrl2", 2, "Short Description2", "Thumbnail2", "Title2")
        )
        every { mockRepository.getFreeGames() } returns flowOf(Resource.Success(fakeFreeGames))
        val result = useCase.invoke().first()
        assertTrue(result is Resource.Success)
        assertEquals(
            "Data list size does not match",
            fakeFreeGames.size,
            (result as Resource.Success<List<FreeGames>>).data?.size
        )
    }

    @Test
    fun `invoke should return Error when repository returns an error`() = runBlocking {
        val errorMessage = "An error occurred"
        every { mockRepository.getFreeGames() } returns flowOf(Resource.Error(errorMessage))
        val result = useCase.invoke().first()
        assertTrue(result is Resource.Error)
        assertEquals("Error message does not match", errorMessage, (result as Resource.Error).msg)
    }
}

