package com.example.teamup.projects.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teamup.core.model.Resource
import com.example.teamup.projects.domain.use_case.FetchProjectsUseCase
import com.example.teamup.projects.presentation.ui.event.ProjectsEvent
import com.example.teamup.projects.presentation.ui.state.ProjectItemUiState
import com.example.teamup.projects.presentation.ui.state.ProjectsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectsViewModel @Inject constructor(
    val fetchProjectsUseCase: FetchProjectsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProjectsUiState())
    val uiState: StateFlow<ProjectsUiState> = _uiState


    fun onEvent(event: ProjectsEvent) {
        when (event) {
            is ProjectsEvent.ProjectItemClicked -> selectProject(event.projectId)
        }
    }

    private fun selectProject(projectId: String) {
        _uiState.update {
            it.copy(

                selectedProjectId = projectId
            )
        }
    }


    fun fetchProjects() {
        viewModelScope.launch {
            fetchProjectsUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                generalErrorMessage = result.message
                            )
                        }

                    }

                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                projectItems = result.data?.map { project ->
                                    ProjectItemUiState(
                                        id = project.id,
                                        title = project.title,
                                        summary = project.summary,
                                        mentorCareer = project.mentorCareer,
                                        mentorId = project.mentorId,
                                        mentorUserName = project.mentorUserName,
                                        mentorProfileImageUrl = project.mentorProfileImageUrl,
                                        categories = project.categories,
                                        postingTime = project.postingTime
                                    )
                                } ?: listOf()
                            )
                        }
                    }
                }
            }
        }
    }

}