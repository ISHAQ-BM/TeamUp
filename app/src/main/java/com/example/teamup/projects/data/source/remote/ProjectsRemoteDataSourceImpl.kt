package com.example.teamup.projects.data.source.remote

import com.example.teamup.core.BaseRemoteDataSource
import com.example.teamup.core.model.Resource
import com.example.teamup.projects.data.source.remote.model.Project
import com.example.teamup.projects.data.source.remote.model.ProjectDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProjectsRemoteDataSourceImpl @Inject constructor(
    private val projectsApi: ProjectsApi
) : ProjectsRemoteDataSource, BaseRemoteDataSource() {
    override suspend fun fetchProjects(): Flow<Resource<List<Project>>> {

        /*return safeApiCall { projectsApi.fetchProjects() }.map {
            when(it){
                is Resource.Success -> { Resource.Success(
                    it.data?.projects ?: emptyList())
                }
                is Resource.Loading -> {Resource.Loading() }
                is Resource.Error ->  {Resource.Error(it.message) }
            }
        }*/
        return flow {
            emit(
                Resource.Success(
                    listOf(
                        Project(
                            id = "project_1",
                            title = "Develop a mobile app for fitness tracking",
                            summary = "This project aims to create a user-friendly mobile application that helps users track their fitness activities and progress.",
                            mentorCareer = "Software Engineer",
                            mentorId = "mentor_abc",
                            mentorUserName = "Alice Smith",
                            mentorProfileImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTap6CR4Ge_5Wri3xUZxaibeNZsgJDSCwn7bw&s",
                            categories = listOf("Web", "Design"),
                            postingTime = "2 days ago"
                        ),
                        Project(
                            id = "project_2",
                            title = "Design a user interface for a music streaming service",
                            summary = "This project involves designing a user-friendly and visually appealing interface for a music streaming service.",
                            mentorCareer = "UX/UI Designer",
                            mentorId = "mentor_xyz",
                            mentorUserName = "David Jones",
                            mentorProfileImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSBlPlpTtK_z4wQ4W74DmV5pxpZYatxBAmzrg&s",
                            categories = listOf("Mobile", "Ai"),
                            postingTime = "1 week ago"
                        ),
                        Project(
                            id = "project_3",
                            title = "Develop a machine learning model for stock price prediction",
                            summary = "This project focuses on building a machine learning model that can predict stock prices with a certain degree of accuracy.",
                            mentorCareer = "Data Scientist",
                            mentorId = "mentor_def",
                            mentorUserName = "Emily Williams",
                            mentorProfileImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSM8a2MUfdySK0SpBsRoLJ7GvrRP0mMIkixcw&s",
                            categories = listOf("Data Science", "Game"),
                            postingTime = "3 weeks ago"
                        ),
                        Project(
                            id = "project_4",
                            title = "Create a marketing campaign for a new social media platform",
                            summary = "This project requires developing a creative and effective marketing campaign to launch a new social media platform.",
                            mentorCareer = "Marketing Specialist",
                            mentorId = "mentor_ghi",
                            mentorUserName = "Charles Brown",
                            mentorProfileImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSM8a2MUfdySK0SpBsRoLJ7GvrRP0mMIkixcw&s",
                            categories = listOf("Ai", "Game"),
                            postingTime = "1 month ago"
                        ),
                        Project(
                            id = "project_5",
                            title = "Write technical documentation for a software library",
                            summary = "This project involves creating clear and concise technical documentation for a newly developed software library.",
                            mentorCareer = "Technical Writer",
                            mentorId = "mentor_jkl",
                            mentorUserName = "Olivia Garcia",
                            mentorProfileImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSBlPlpTtK_z4wQ4W74DmV5pxpZYatxBAmzrg&s",
                            categories = listOf("Mobile"),
                            postingTime = "2 months ago"
                        )
                    )
                )
            )
        }
    }

    override suspend fun fetchProjectDetails(id: String): Flow<Resource<ProjectDetails>> {
        return flow {
            emit(

                Resource.Success(
                    ProjectDetails(
                        id = "prj_mobile_app",
                        title = "Develop a mobile fitness tracking app",
                        categories = listOf("Mobile", "Ai"),
                        skills = listOf("Kotlin", "Android", "Firebase"),
                        postingTime = "1 week ago",
                        level = "Beginner",
                        duration = "1 month",
                        teamSize = 5,
                        summary = "This project aims to create a user-friendly mobile app that tracks fitness activities and provides personalized workout routines.",
                        mentorCareer = "Software Engineer",
                        mentorId = "mentor_john_doe",
                        mentorUserName = "John Doe",
                        mentorProfileImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSBlPlpTtK_z4wQ4W74DmV5pxpZYatxBAmzrg&s",
                        mentorRating = 4.8,
                        projectScenario = "Users can log their workouts, set goals, and track progress. The app will integrate with wearable devices for data collection.",
                        learningGoal = "Gain exGain experience in building user interfaces, data management, and fitness app functionalities",
                        teamAndRoles = "Gain experience in building user interfaces, data management, and fitness app functionalities"
                    )
                )
            )

        }
    }
}