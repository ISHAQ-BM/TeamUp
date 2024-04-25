package com.example.teamup.mentors.domain.model

data class Mentor(
    val id: String,
    val name: String,
    val profession: String,
    val mentorProfileImageUrl: String,
    val averageRating: Double,
    val numberOfReviews: Int,
    var isFollowing: Boolean,
)
