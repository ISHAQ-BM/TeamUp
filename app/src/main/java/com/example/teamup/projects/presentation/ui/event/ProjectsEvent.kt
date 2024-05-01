package com.example.teamup.projects.presentation.ui.event

sealed class ProjectsEvent {
    data class ProjectItemClicked(val projectId: String) : ProjectsEvent()
}