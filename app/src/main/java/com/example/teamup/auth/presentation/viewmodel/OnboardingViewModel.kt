package com.example.teamup.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.teamup.auth.presentation.ui.event.OnboardingEvent
import com.example.teamup.auth.presentation.ui.state.OnboardingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(

):ViewModel() {
    private val _uiState=MutableStateFlow(OnboardingUiState())
    val uiState:StateFlow<OnboardingUiState> = _uiState


    fun onEvent(event:OnboardingEvent){
        when(event){
            is OnboardingEvent.UserChoiceChanged ->{
                saveUserAnswer()
            }
        }
    }

    private fun saveUserAnswer() {
        TODO("Not yet implemented")
    }
}