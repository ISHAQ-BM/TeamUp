package com.example.teamup.mentors.presentation.ui.state

data class MentorItemUiState(
    val id: String,
    val name: String,
    val profileImageUrl: String,
    val profession: String,
    val averageRating: Double,
    val reviewsNumber: Int,
    var isFollowing: Boolean
)