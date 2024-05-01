package com.example.teamup.projects.presentation.ui.state

data class ProjectsUiState(
    val projectItems: List<ProjectItemUiState> = listOf(),
    val selectedProjectId: String? = null,
    val isLoading: Boolean = false,
    val generalErrorMessage: String? = null,
)
