package com.example.teamup.projects.data.source.remote.model


import com.google.gson.annotations.SerializedName

data class ProjectsResponse(
    @SerializedName("isNextPageExist")
    val isNextPageExist: Boolean,
    @SerializedName("isPrevPageExist")
    val isPrevPageExist: Boolean,
    @SerializedName("pageNumber")
    val pageNumber: Int,
    @SerializedName("pageSize")
    val pageSize: Int,
    @SerializedName("projects")
    val projects: List<Project>,
    @SerializedName("totalCount")
    val totalCount: Int
)