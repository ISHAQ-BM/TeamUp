package com.example.teamup.main.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teamup.core.model.Resource
import com.example.teamup.main.domain.use_case.GetCurrentUserUseCase
import com.example.teamup.main.presentation.ui.state.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {


    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState


    fun getCurrentUser() {
        viewModelScope.launch {
            getCurrentUserUseCase().collect { result ->
                /*if (result.id != null){
                    Log.d("test user", "${result}")
                    _uiState.update {
                        it.copy(
                            isUserLoggedIn = true,
                            isEmailConfirmed = result.isEmailConfirmed
                        )
                    }
                }*/

                when (result) {
                    is Resource.Error -> {
                        Log.d("test errorr", result.message.toString())
                    }

                    is Resource.Loading -> {
                        Unit
                    }

                    is Resource.Success -> {
                        Log.d("test user", "${result.data}")
                        _uiState.update {
                            it.copy(
                                isUserLoggedIn = true,
                                isEmailConfirmed = result.data?.isEmailConfirmed
                            )
                        }
                    }
                }
            }
        }
    }

}