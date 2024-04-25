package com.example.teamup.main.domain.use_case

import com.example.teamup.main.domain.repository.MainRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke() = mainRepository.getCurrentUser()
}