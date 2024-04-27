package com.example.teamup.mentors.data.source.remote.model

data class Mentor(
    val id: String,
    val name: String,
    val profession: String,
    val mentorProfileImageUrl: String,
    val averageRating: Double,
    val numberOfReviews: Int,
    val isFollowing: Boolean,
)
