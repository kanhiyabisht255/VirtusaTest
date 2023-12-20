package com.kanhiyabisht.virtusaandroidtest.domain.usecase

import com.kanhiyabisht.virtusaandroidtest.domain.repository.FreeGameRepository
import javax.inject.Inject

open class FreeGameUseCase @Inject constructor(private val repository: FreeGameRepository) {
    operator fun invoke() = repository.getFreeGames()
}