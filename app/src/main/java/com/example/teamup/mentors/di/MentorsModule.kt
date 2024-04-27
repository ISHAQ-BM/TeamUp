package com.example.teamup.mentors.di


import com.example.teamup.mentors.data.repository.MentorsRepositoryImpl
import com.example.teamup.mentors.data.source.remote.MentorsRemoteDataSource
import com.example.teamup.mentors.data.source.remote.MentorsRemoteDataSourceImpl
import com.example.teamup.mentors.domain.repository.MentorsRepository
import com.example.teamup.mentors.domain.use_case.FetchMentorsUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MentorsModule {

    @Provides
    @Singleton
    fun provideFetchMentorsUseCase(mentorsRepository: MentorsRepository) =
        FetchMentorsUseCase(mentorsRepository)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMentorsRepository(mentorsRepositoryImpl: MentorsRepositoryImpl): MentorsRepository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindMentorsRemoteDataSource(mentorsRemoteDataSourceImpl: MentorsRemoteDataSourceImpl): MentorsRemoteDataSource

}


