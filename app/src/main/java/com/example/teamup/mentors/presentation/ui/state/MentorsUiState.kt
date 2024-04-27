package com.example.teamup.mentors.presentation.ui.state

data class MentorsUiState(
    val mentorItems: List<MentorItemUiState> = listOf(),
    val selectedMentorId: String? = null,
    val isLoading: Boolean = false,
    val generalErrorMessage: String? = null,

    )