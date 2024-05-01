package com.example.teamup.projects.domain.repository

import com.example.teamup.core.model.Resource
import com.example.teamup.projects.data.source.remote.model.Project
import com.example.teamup.projects.data.source.remote.model.ProjectDetails
import kotlinx.coroutines.flow.Flow

interface ProjectsRepository {
    suspend fun fetchProjects(): Flow<Resource<List<Project>>>
    suspend fun fetchProjectDetails(id: String): Flow<Resource<ProjectDetails>>
}