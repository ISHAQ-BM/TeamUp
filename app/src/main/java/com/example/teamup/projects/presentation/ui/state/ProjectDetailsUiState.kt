package com.example.teamup.projects.presentation.ui.state

data class ProjectDetailsUiState(
    val title: String? = "",
    val categories: List<String>? = listOf(),
    val skills: List<String>? = listOf(),
    val postingTime: String? = "",
    val level: String? = "",
    val duration: String? = "",
    val teamSize: Int? = 0,
    val summary: String? = "",
    val mentorCareer: String? = "",
    val mentorId: String? = "",
    val mentorRating: Double? = 0.0,
    val mentorUserName: String? = "",
    val mentorProfileImageUrl: String? = "",
    val projectScenario: String? = "",
    val learningGoal: String? = "",
    val teamAndRoles: String? = "",
    val isLoading: Boolean = false,
    val generalErrorMessage: String? = null,

    )