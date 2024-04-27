package com.example.teamup.mentors.data.repository

import com.example.teamup.core.model.Resource
import com.example.teamup.mentors.data.source.remote.MentorsRemoteDataSource
import com.example.teamup.mentors.data.source.remote.model.Mentor
import com.example.teamup.mentors.domain.repository.MentorsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MentorsRepositoryImpl @Inject constructor(
    private val mentorsRemoteDataSource: MentorsRemoteDataSource
) : MentorsRepository {
    override suspend fun fetchMentors(): Flow<Resource<List<Mentor>>> =
        mentorsRemoteDataSource.fetchMentors()
}