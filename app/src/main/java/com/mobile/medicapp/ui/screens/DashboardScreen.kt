package com.mobile.medicapp.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mobile.medicapp.ui.component.Loading
import com.mobile.medicapp.ui.component.MedicineDetail
import com.mobile.medicapp.ui.component.MedicineList
import com.mobile.medicapp.ui.mapper.toMedicinePresentation
import com.mobile.medicapp.ui.model.MedicinePresentation
import com.mobile.medicapp.ui.viewmodel.DashboardViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    dashboardViewModel: DashboardViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState
) {

    val dashboardState by dashboardViewModel.dashboardState.collectAsStateWithLifecycle()

    val navigator = rememberListDetailPaneScaffoldNavigator<MedicinePresentation>()

    val scope = rememberCoroutineScope()

    Box {
        if (dashboardState.isLoading) {
            Loading()
        } else {

            BackHandler(navigator.canNavigateBack()) {
                navigator.navigateBack()
            }

            ListDetailPaneScaffold(directive = navigator.scaffoldDirective,
                value = navigator.scaffoldValue,
                listPane = {
                    AnimatedPane {
                        MedicineList(modifier = modifier,
                            dashboardState = dashboardState,
                            dashboardViewModel = dashboardViewModel,
                            onMedicineClicked = { medicine ->
                                navigator.navigateTo(
                                    ListDetailPaneScaffoldRole.Detail,
                                    medicine.toMedicinePresentation()
                                )
                            })
                    }
                },
                detailPane = {
                    AnimatedPane {
                        navigator.currentDestination?.content?.let { medicinePresentation ->
                            MedicineDetail(
                                medicinePresentation = medicinePresentation, modifier = modifier
                            )
                        }
                    }
                })
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