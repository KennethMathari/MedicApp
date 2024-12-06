package com.mobile.medicapp.ui.state

import com.mobile.medicapp.data.network.model.MedicineListDTO

data class DashboardState(
    val isLoading: Boolean = false,
    val medicineList: MedicineListDTO? = null,
    val errorMessage: String? = null
)
