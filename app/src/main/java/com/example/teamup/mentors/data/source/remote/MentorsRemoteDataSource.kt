package com.example.teamup.mentors.data.source.remote

import com.example.teamup.core.model.Resource
import com.example.teamup.mentors.data.source.remote.model.Mentor
import kotlinx.coroutines.flow.Flow

interface MentorsRemoteDataSource {
    suspend fun fetchMentors(): Flow<Resource<List<Mentor>>>
}