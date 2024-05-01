package com.example.teamup.projects.presentation.ui.state

data class ProjectItemUiState(
    val id: String,
    val title: String,
    val summary: String,
    val mentorCareer: String,
    val mentorId: String,
    val mentorUserName: String,
    val mentorProfileImageUrl: String,
    val categories: List<String>,
    val postingTime: String
)
