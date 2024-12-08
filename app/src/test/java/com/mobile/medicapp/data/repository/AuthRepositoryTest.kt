package com.mobile.medicapp.data.repository

import com.mobile.medicapp.domain.repository.AuthRepository
import com.mobile.medicapp.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AuthRepositoryTest {
    private val authRepository: AuthRepository = mockk()

    @Test
    fun userLoginReturnsSuccess() = runTest {
        coEvery {
            authRepository.userLogin(email = "user@test.com", password = "12345")
        } returns NetworkResult.Success(true)

        val result = authRepository.userLogin(email = "user@test.com", password = "12345")

        assertEquals(NetworkResult.Success(true), result)
    }

    @Test
    fun userLoginReturnsNetworkError() = runTest {
        val expectedException = Exception("Login failed")

        coEvery {
            authRepository.userLogin(email = "user@test.com", password = "12345")
        } returns NetworkResult.NetworkError(expectedException)

        val result = authRepository.userLogin(email = "user@test.com", password = "12345")

        assertEquals(NetworkResult.NetworkError(expectedException), result)
    }
}