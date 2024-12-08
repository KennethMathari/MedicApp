package com.mobile.medicapp.data

import com.mobile.medicapp.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FirebaseAuthDataSourceTest {

    private val firebaseAuthDataSource: FirebaseAuthDataSource = mockk()

    @Test
    fun loginReturnsSuccess() = runTest {
        coEvery {
            firebaseAuthDataSource.login(email = "user@test.com", password = "12345")
        } returns NetworkResult.Success(true)

        val result = firebaseAuthDataSource.login(email = "user@test.com", password = "12345")

        assertEquals(NetworkResult.Success(true), result)
    }

    @Test
    fun loginReturnsClientError() = runTest {
        val expectedException = Exception("Login failed")

        coEvery {
            firebaseAuthDataSource.login(email = "user@test.com", password = "12345")
        } returns NetworkResult.ClientError(expectedException)

        val result = firebaseAuthDataSource.login(email = "user@test.com", password = "12345")

        assertEquals(NetworkResult.ClientError(expectedException), result)
    }
}