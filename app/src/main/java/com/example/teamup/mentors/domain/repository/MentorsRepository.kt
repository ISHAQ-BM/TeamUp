package com.example.teamup.mentors.domain.repository

import com.example.teamup.core.model.Resource
import com.example.teamup.mentors.data.source.remote.model.Mentor
import kotlinx.coroutines.flow.Flow

interface MentorsRepository {
    suspend fun fetchMentors(): Flow<Resource<List<Mentor>>>
}