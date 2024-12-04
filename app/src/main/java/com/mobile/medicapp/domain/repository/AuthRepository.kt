package com.mobile.medicapp.domain.repository

import com.mobile.medicapp.utils.NetworkResult

interface AuthRepository {

    suspend fun userLogin(email: String, password: String): NetworkResult<Boolean>
}