package com.mobile.medicapp.ui.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mobile.medicapp.ui.navigation.destination.Login
import com.mobile.medicapp.ui.screens.LoginScreen

@Composable
fun Navigation(
    navHostController: NavHostController, snackbarHostState: SnackbarHostState
) {

    NavHost(
        navController = navHostController, startDestination = Login
    ) {
        composable<Login> {
            LoginScreen(
                snackbarHostState = snackbarHostState
            )
        }
    }

}