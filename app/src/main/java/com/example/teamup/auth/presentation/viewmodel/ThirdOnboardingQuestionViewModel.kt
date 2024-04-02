package com.example.teamup.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.teamup.auth.presentation.ui.event.SecondOnboardingQuestionEvent
import com.example.teamup.auth.presentation.ui.event.ThirdOnboardingQuestionEvent
import com.example.teamup.auth.presentation.ui.state.SecondOnboardingQuestionUiState
import com.example.teamup.auth.presentation.ui.state.ThirdOnboardingQuestionUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class ThirdOnboardingQuestionViewModel @Inject constructor(

): ViewModel() {

    private val _uiState = MutableStateFlow(ThirdOnboardingQuestionUiState())
    val uiState: StateFlow<ThirdOnboardingQuestionUiState> =_uiState

    fun onEvent(event: ThirdOnboardingQuestionEvent){
        when(event){
            is ThirdOnboardingQuestionEvent.NextClicked -> {
                if (_uiState.value.selectedChoiceIndex == -1)
                    _uiState.update { it.copy(generalErrorMessage = "Please select an answer to continue") }

            }


            is ThirdOnboardingQuestionEvent.SelectedAnswerChanged -> {
                _uiState.update { it.copy(
                    selectedChoiceIndex = event.selectedChoiceIndex)
                }
            }
        }
    }

}