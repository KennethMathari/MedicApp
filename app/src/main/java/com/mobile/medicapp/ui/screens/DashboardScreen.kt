package com.mobile.medicapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mobile.medicapp.ui.viewmodel.DashboardViewModel

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier, dashboardViewModel: DashboardViewModel = hiltViewModel()
) {

    val user = Firebase.auth.currentUser?.email
    val greeting = dashboardViewModel.getGreeting()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "$greeting, $user!")
    }
}