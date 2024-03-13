package com.example.teamup.auth.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teamup.auth.domain.use_case.AuthUseCase
import com.example.teamup.auth.presentation.ui.event.SignUpEvent
import com.example.teamup.auth.presentation.ui.state.SignUpUiState
import com.example.teamup.core.model.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
):ViewModel() {
    private val _uiState = MutableLiveData<SignUpUiState>()
    val uiState: LiveData<SignUpUiState> = _uiState


    fun onEvent(event: SignUpEvent){
        when(event){
            is SignUpEvent.EmailChanged -> {
                _uiState.value =_uiState.value?.copy(email =event.email)
            }
            is SignUpEvent.PasswordChanged -> {
                _uiState.value =_uiState.value?.copy(password =event.password)
            }
            is SignUpEvent.SignUpWithGoogle -> {
                signUpWithGoogle()
            }
            is SignUpEvent.SignUpWithGithub -> {

            }
            is SignUpEvent.SignUp-> {
                if (isUserInputsValid())
                    signUp()
            }
        }
    }

    private fun signUpWithGoogle() {
        _uiState.value = _uiState.value?.copy(
            isLoading = true
        )
        viewModelScope.launch {
            authUseCase.signUserWithOneTapUseCase().also {
                when(it){
                    is Resource.Success ->{
                        _uiState.value=_uiState.value?.copy(
                            isLoading = false,
                        )
                    }
                    is Resource.Error -> {
                        _uiState.value=_uiState.value!!.copy(
                            isLoading = false
                        )
                    }
                    else ->{}

                }
            }
        }
    }

    private fun signUp() {
        _uiState.value = _uiState.value?.copy(
            isLoading = true
        )
        viewModelScope.launch {

            authUseCase.logInWithEmailAndPasswordUseCase(
                _uiState.value?.email!!,
                _uiState.value?.password!!
            ).also {
                when(it){
                    is Resource.Success ->{
                        _uiState.value=_uiState.value?.copy(
                            isLoading = false,

                        )
                    }
                    is Resource.Error -> {
                        _uiState.value=_uiState.value!!.copy(
                            isLoading = false
                        )
                    }
                    else ->{}


                }
            }
        }
    }

    private fun isUserInputsValid():Boolean {
        val emailValidationResult =
            _uiState.value?.let { authUseCase.validateEmailUseCase(it.email) }
        val passwordValidationResult =
            _uiState.value?.let { authUseCase.validatePasswordUseCase(it.password) }

        val hasError =
            emailValidationResult?.successful == false && passwordValidationResult?.successful == false

        if (hasError) {
            _uiState.value = _uiState.value!!.copy(
                emailError = emailValidationResult?.errorMessage,
                passwordError = passwordValidationResult?.errorMessage
            )
            return false
        }
        return true

    }

}