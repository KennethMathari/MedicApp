package com.mobile.medicapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mobile.medicapp.ui.component.Loading
import com.mobile.medicapp.ui.viewmodel.DashboardViewModel
import kotlinx.coroutines.launch

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    dashboardViewModel: DashboardViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState
) {

    val dashboardState by dashboardViewModel.dashboardState.collectAsStateWithLifecycle()

    val user = Firebase.auth.currentUser?.email
    val greeting = dashboardViewModel.getGreeting()

    val scope = rememberCoroutineScope()

    Box {
        if (dashboardState.isLoading) {
            Loading()
        } else {
            Column(
                modifier = modifier.fillMaxSize()
            ) {
                Text(
                    text = "$greeting, $user!",
                    modifier = modifier.padding(10.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                LazyColumn {
                    items(dashboardState.medicineList?.medicine ?: emptyList()) { medicine ->

                        Text(text = medicine.name)

                    }
                }
            }
        }
    }

    LaunchedEffect(dashboardState.errorMessage) {
        scope.launch {
            dashboardState.errorMessage?.let { errorMessage ->
                snackbarHostState.showSnackbar(errorMessage)
            }
        }
    }

}