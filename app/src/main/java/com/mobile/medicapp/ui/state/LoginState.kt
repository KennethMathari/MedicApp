package com.mobile.medicapp.ui.state

data class LoginState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val errorMessage: String? = null
)
