package com.example.teamup.auth.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.teamup.auth.presentation.ui.event.FirstOnboardingQuestionEvent
import com.example.teamup.auth.presentation.ui.state.FirstOnboardingQuestionUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class FirstOnboardingQuestionViewModel @Inject constructor(

):ViewModel() {

    private val _uiState = MutableStateFlow(FirstOnboardingQuestionUiState())
    val uiState: StateFlow<FirstOnboardingQuestionUiState> =_uiState

    fun onEvent(event:FirstOnboardingQuestionEvent){
        when(event){
            is FirstOnboardingQuestionEvent.NextClicked -> {
                if (_uiState.value.selectedChoiceIndex == -1)
                    _uiState.update { it.copy(generalErrorMessage = "Please select an answer to continue") }

            }


            is FirstOnboardingQuestionEvent.SelectedAnswerChanged -> {
                _uiState.update { it.copy(
                    selectedChoiceIndex = event.selectedChoiceIndex)
                }
            }
        }
    }

}