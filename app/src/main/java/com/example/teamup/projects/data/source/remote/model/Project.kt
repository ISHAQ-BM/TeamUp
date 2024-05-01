package com.example.teamup.projects.data.source.remote.model

data class Project(
    val id: String,
    val title: String,
    val summary: String,
    val mentorCareer: String,
    val mentorId: String,
    val mentorUserName: String,
    val mentorProfileImageUrl: String,
    val categories: List<String>,
    var postingTime: String
)
