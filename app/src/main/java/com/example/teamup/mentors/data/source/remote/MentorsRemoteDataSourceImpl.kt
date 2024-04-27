package com.example.teamup.mentors.data.source.remote

import com.example.teamup.core.model.Resource
import com.example.teamup.mentors.data.source.remote.model.Mentor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MentorsRemoteDataSourceImpl @Inject constructor() : MentorsRemoteDataSource {
    override suspend fun fetchMentors(): Flow<Resource<List<Mentor>>> {
        return flow {
            emit(
                Resource.Success(
                    listOf(
                        Mentor(
                            id = "mentor_1",
                            name = "Alice Smith",
                            mentorProfileImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTap6CR4Ge_5Wri3xUZxaibeNZsgJDSCwn7bw&s",
                            profession = "Software Engineer",
                            averageRating = 4.8,
                            numberOfReviews = 25,
                            isFollowing = false
                        ),
                        Mentor(
                            id = "mentor_2",
                            name = "David Jones",
                            mentorProfileImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSBlPlpTtK_z4wQ4W74DmV5pxpZYatxBAmzrg&s",
                            profession = "Data Scientist",
                            averageRating = 4.9,
                            numberOfReviews = 87,
                            isFollowing = true
                        ),
                        Mentor(
                            id = "mentor_3",
                            name = "Emily Williams",
                            mentorProfileImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSM8a2MUfdySK0SpBsRoLJ7GvrRP0mMIkixcw&s",
                            profession = "UX/UI Designer",
                            averageRating = 4.7,
                            numberOfReviews = 12,
                            isFollowing = false
                        ),
                        Mentor(
                            id = "mentor_4",
                            name = "Charles Brown",
                            mentorProfileImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSBlPlpTtK_z4wQ4W74DmV5pxpZYatxBAmzrg&s",
                            profession = "Product Manager",
                            averageRating = 4.5,
                            numberOfReviews = 54,
                            isFollowing = true
                        ),
                        Mentor(
                            id = "mentor_5",
                            name = "Olivia Garcia",
                            mentorProfileImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSM8a2MUfdySK0SpBsRoLJ7GvrRP0mMIkixcw&s",
                            profession = "Marketing Specialist",
                            averageRating = 4.2,
                            numberOfReviews = 31,
                            isFollowing = false
                        ),
                        Mentor(
                            id = "mentor_3",
                            name = "Emily Williams",
                            mentorProfileImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSM8a2MUfdySK0SpBsRoLJ7GvrRP0mMIkixcw&s",
                            profession = "UX/UI Designer",
                            averageRating = 4.7,
                            numberOfReviews = 12,
                            isFollowing = false
                        )
                    )
                )
            )
        }
    }
}