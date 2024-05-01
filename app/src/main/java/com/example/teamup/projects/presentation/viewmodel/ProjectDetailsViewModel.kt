package com.example.teamup.projects.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teamup.core.model.Resource
import com.example.teamup.projects.domain.use_case.FetchProjectDetailsUseCase
import com.example.teamup.projects.presentation.ui.event.ProjectDetailsEvent
import com.example.teamup.projects.presentation.ui.state.ProjectDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectDetailsViewModel @Inject constructor(
    val fetchProjectDetailsUseCase: FetchProjectDetailsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProjectDetailsUiState())
    val uiState: StateFlow<ProjectDetailsUiState> = _uiState

    fun onEvent(event: ProjectDetailsEvent) {
        when (event) {
            is ProjectDetailsEvent.ProjectIdChanged -> fetchProjectDetails(event.id)
        }
    }


    private fun fetchProjectDetails(id: String) {
        viewModelScope.launch {
            fetchProjectDetailsUseCase(id).collect { result ->
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
                                title = result.data?.title,
                                summary = result.data?.summary,
                                postingTime = result.data?.postingTime,
                                level = result.data?.level,
                                duration = result.data?.duration,
                                teamSize = result.data?.teamSize,
                                categories = result.data?.categories,
                                skills = result.data?.skills,
                                mentorProfileImageUrl = result.data?.mentorProfileImageUrl,
                                mentorUserName = result.data?.mentorUserName,
                                mentorCareer = result.data?.mentorCareer,
                                mentorRating = result.data?.mentorRating,
                                projectScenario = result.data?.projectScenario,
                                learningGoal = result.data?.learningGoal,
                                teamAndRoles = result.data?.teamAndRoles,
                            )
                        }
                    }
                }
            }
        }
    }
}