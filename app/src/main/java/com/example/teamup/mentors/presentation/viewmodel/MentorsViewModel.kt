package com.example.teamup.mentors.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teamup.core.model.Resource
import com.example.teamup.mentors.domain.use_case.FetchMentorsUseCase
import com.example.teamup.mentors.presentation.ui.event.MentorEvent
import com.example.teamup.mentors.presentation.ui.event.MentorsEvent
import com.example.teamup.mentors.presentation.ui.state.MentorItemUiState
import com.example.teamup.mentors.presentation.ui.state.MentorsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MentorsViewModel @Inject constructor(
    private val fetchMentorsUseCase: FetchMentorsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(MentorsUiState())
    val uiState: StateFlow<MentorsUiState> = _uiState


    fun onEvent(event: MentorsEvent) {
        when (event) {
            is MentorsEvent.MentorItemClicked -> selectMentor(event.mentorId)
        }
    }

    fun onEvent(event: MentorEvent) {
        when (event) {
            is MentorEvent.FollowClicked -> toggleFollow(event.mentorId)
        }
    }

    private fun toggleFollow(mentorId: String) {
        _uiState.update {
            val updatedMentors = it.mentorItems.map { mentor ->
                if (mentor.id == mentorId) {
                    mentor.copy(isFollowing = !mentor.isFollowing)
                } else {
                    mentor
                }
            }
            it.copy(
                mentorItems = updatedMentors
            )
        }
    }

    private fun selectMentor(mentorId: String) {
        _uiState.update {
            it.copy(
                selectedMentorId = mentorId
            )
        }
    }

    fun fetchMentors() {
        viewModelScope.launch {
            fetchMentorsUseCase().collect { result ->
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
                                mentorItems = result.data?.map { mentor ->
                                    MentorItemUiState(
                                        id = mentor.id,
                                        name = mentor.name,
                                        profileImageUrl = mentor.mentorProfileImageUrl,
                                        profession = mentor.profession,
                                        averageRating = mentor.averageRating,
                                        reviewsNumber = mentor.numberOfReviews,
                                        isFollowing = mentor.isFollowing
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