package com.mobile.medicapp.data

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mobile.medicapp.utils.NetworkResult
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class FirebaseAuthDataSource {
    private val auth = Firebase.auth

    suspend fun login(email: String, password: String): NetworkResult<Boolean> {
        return try {
            suspendCancellableCoroutine { continuation ->
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resume(NetworkResult.Success(true))
                    } else {
                        continuation.resume(
                            NetworkResult.NetworkError(
                                task.exception?.cause ?: Exception("Login failed")
                            )
                        )
                    }
                }
            }
        } catch (e: Exception) {
            NetworkResult.ClientError(e.cause ?: Exception(e.message))
        }
    }
}