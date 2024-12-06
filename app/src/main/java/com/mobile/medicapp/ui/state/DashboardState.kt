package com.mobile.medicapp.ui.state

import com.mobile.medicapp.data.network.model.Medicine

data class DashboardState(
    val isLoading: Boolean = false,
    val medicineList: List<Medicine>? = null,
    val errorMessage: String? = null
)
