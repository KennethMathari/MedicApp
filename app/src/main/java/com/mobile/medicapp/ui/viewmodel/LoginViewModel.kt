package com.mobile.medicapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.medicapp.domain.repository.AuthRepository
import com.mobile.medicapp.ui.state.LoginState
import com.mobile.medicapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> get() = _loginState.asStateFlow()

    fun userLogin(email: String, password: String){
    viewModelScope.launch {
        _loginState.value = LoginState(
            isLoading = true,
            success = false,
            errorMessage = null
        )

        val result = authRepository.userLogin(email, password)

        when(result){
            is NetworkResult.ClientError -> {
                updateErrorMessage("Unable to Login! Please Try Again.")
            }
            is NetworkResult.NetworkError -> {
                updateErrorMessage("Unable to Login! Check Internet Connection.")
            }
            is NetworkResult.ServerError -> {
                updateErrorMessage("Oops! Our Server is Down")
            }
            is NetworkResult.Success -> {
                _loginState.value = LoginState(
                    isLoading = false,
                    success = true,
                    errorMessage = null
                )
            }
        }
    }
    }

    private fun updateErrorMessage(errorMessage: String){
        _loginState.value = LoginState(
            isLoading = false,
            success = false,
            errorMessage = errorMessage
        )
    }
}