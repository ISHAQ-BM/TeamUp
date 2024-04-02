package com.example.teamup.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.teamup.auth.presentation.ui.event.SecondOnboardingQuestionEvent
import com.example.teamup.auth.presentation.ui.state.SecondOnboardingQuestionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SecondOnboardingQuestionViewModel @Inject constructor(

): ViewModel() {

    private val _uiState = MutableStateFlow(SecondOnboardingQuestionUiState())
    val uiState: StateFlow<SecondOnboardingQuestionUiState> =_uiState

    fun onEvent(event: SecondOnboardingQuestionEvent){
        when(event){
            is SecondOnboardingQuestionEvent.NextClicked -> {
                if (_uiState.value.selectedChoiceIndex == -1)
                    _uiState.update { it.copy(generalErrorMessage = "Please select an answer to continue") }

            }


            is SecondOnboardingQuestionEvent.SelectedAnswerChanged -> {
                _uiState.update { it.copy(
                    selectedChoiceIndex = event.selectedChoiceIndex)
                }
            }
        }
    }

}