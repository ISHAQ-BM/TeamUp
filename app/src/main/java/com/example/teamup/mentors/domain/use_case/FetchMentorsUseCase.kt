package com.example.teamup.mentors.domain.use_case

import com.example.teamup.mentors.domain.repository.MentorsRepository
import javax.inject.Inject

class FetchMentorsUseCase @Inject constructor(
    private val mentorRepository: MentorsRepository
) {
    suspend operator fun invoke() = mentorRepository.fetchMentors()
}