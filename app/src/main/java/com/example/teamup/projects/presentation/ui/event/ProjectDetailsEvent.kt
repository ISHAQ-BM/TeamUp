package com.example.teamup.projects.presentation.ui.event

sealed class ProjectDetailsEvent {
    data class ProjectIdChanged(val id: String) : ProjectDetailsEvent()
}