package com.example.teamup.projects.data.source.remote

import com.example.teamup.projects.data.source.remote.model.ProjectsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProjectsApi {

    @GET("v1/projects")
    suspend fun fetchProjects(): Response<ProjectsResponse>
}