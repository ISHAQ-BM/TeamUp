package com.example.teamup.projects.data.repository

import com.example.teamup.core.model.Resource
import com.example.teamup.projects.data.source.remote.ProjectsRemoteDataSource
import com.example.teamup.projects.data.source.remote.model.Project
import com.example.teamup.projects.data.source.remote.model.ProjectDetails
import com.example.teamup.projects.domain.repository.ProjectsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProjectsRepositoryImpl @Inject constructor(
    private val projectsRemoteDataSource: ProjectsRemoteDataSource
) : ProjectsRepository {
    override suspend fun fetchProjects(): Flow<Resource<List<Project>>> =
        projectsRemoteDataSource.fetchProjects()

    override suspend fun fetchProjectDetails(id: String): Flow<Resource<ProjectDetails>> =
        projectsRemoteDataSource.fetchProjectDetails(id)
}