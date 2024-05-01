package com.example.teamup.projects.data.source.remote.model

data class ProjectDetails(
    val id: String,
    val title: String,
    val categories: List<String>,
    val skills: List<String>,
    val postingTime: String,
    val level: String,
    val duration: String,
    val teamSize: Int,
    val summary: String,
    val mentorCareer: String,
    val mentorId: String,
    val mentorUserName: String,
    val mentorProfileImageUrl: String,
    val mentorRating: Double,
    val projectScenario: String,
    val learningGoal: String,
    val teamAndRoles: String
)
