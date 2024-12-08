package com.mobile.medicapp.ui.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mobile.medicapp.ui.navigation.destination.Dashboard
import com.mobile.medicapp.ui.navigation.destination.Login
import com.mobile.medicapp.ui.screens.DashboardScreen
import com.mobile.medicapp.ui.screens.LoginScreen

@Composable
fun Navigation(
    navHostController: NavHostController, snackbarHostState: SnackbarHostState
) {

    val currentUser = Firebase.auth.currentUser

    NavHost(
        navController = navHostController,
        startDestination = if (currentUser != null) Dashboard else Login
    ) {
        composable<Login> {
            LoginScreen(
                snackbarHostState = snackbarHostState,
                onLoginSuccess = {
                    navHostController.navigate(Dashboard)
                }
            )
        }

        composable<Dashboard> {
            DashboardScreen(
                snackbarHostState = snackbarHostState
            )
        }
    }

}