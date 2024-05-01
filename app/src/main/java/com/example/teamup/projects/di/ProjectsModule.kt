package com.example.teamup.projects.di

import com.example.teamup.BuildConfig
import com.example.teamup.projects.data.repository.ProjectsRepositoryImpl
import com.example.teamup.projects.data.source.remote.ProjectsApi
import com.example.teamup.projects.data.source.remote.ProjectsRemoteDataSource
import com.example.teamup.projects.data.source.remote.ProjectsRemoteDataSourceImpl
import com.example.teamup.projects.domain.repository.ProjectsRepository
import com.example.teamup.projects.domain.use_case.FetchProjectsUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProjectsModule {
    @Provides
    @Singleton
    fun provideFetchProjectsUseCase(projectsRepository: ProjectsRepository) =
        FetchProjectsUseCase(projectsRepository)

    @Provides
    @Singleton
    fun provideProjectsApi(okHttpClient: OkHttpClient): ProjectsApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ProjectsApi::class.java)
    }


}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindProjectsRepository(projectsRepositoryImpl: ProjectsRepositoryImpl): ProjectsRepository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindProjectsRemoteDataSource(projectsRemoteDataSourceImpl: ProjectsRemoteDataSourceImpl): ProjectsRemoteDataSource

}