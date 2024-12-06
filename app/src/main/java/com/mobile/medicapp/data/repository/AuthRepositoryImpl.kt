package com.mobile.medicapp.data.repository

import com.mobile.medicapp.data.FirebaseAuthDataSource
import com.mobile.medicapp.domain.repository.AuthRepository
import com.mobile.medicapp.utils.NetworkResult
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuthDataSource: FirebaseAuthDataSource
) : AuthRepository {

    override suspend fun userLogin(email: String, password: String): NetworkResult<Boolean> {
        return firebaseAuthDataSource.login(email, password)
    }
}