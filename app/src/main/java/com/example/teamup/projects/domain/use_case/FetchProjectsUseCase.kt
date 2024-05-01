package com.example.teamup.projects.domain.use_case

import com.example.teamup.projects.domain.repository.ProjectsRepository
import javax.inject.Inject

class FetchProjectsUseCase @Inject constructor(
    private val projectsRepository: ProjectsRepository
) {
    suspend operator fun invoke() = projectsRepository.fetchProjects()
}
