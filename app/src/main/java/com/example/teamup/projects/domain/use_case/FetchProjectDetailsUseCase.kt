package com.example.teamup.projects.domain.use_case

import com.example.teamup.projects.domain.repository.ProjectsRepository
import javax.inject.Inject

class FetchProjectDetailsUseCase @Inject constructor(
    private val projectsRepository: ProjectsRepository
) {
    suspend operator fun invoke(id: String) = projectsRepository.fetchProjectDetails(id)
}