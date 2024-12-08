package com.mobile.medicapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.medicapp.data.network.model.Medicine
import com.mobile.medicapp.domain.repository.MedicineRepository
import com.mobile.medicapp.ui.state.DashboardState
import com.mobile.medicapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val medicineRepository: MedicineRepository
) : ViewModel() {

    private val _dashboardState = MutableStateFlow(DashboardState())
    val dashboardState: StateFlow<DashboardState> get() = _dashboardState.asStateFlow()

    private val _searchMedicine = MutableStateFlow("")

    init {
        getMedicineList()
    }

    fun getGreeting(): String {
        val currentHour = LocalTime.now().hour
        return when (currentHour) {
            in 5..11 -> "Good Morning"
            in 12..16 -> "Good Afternoon"
            in 17..21 -> "Good Evening"
            else -> "Good Night"
        }
    }

    private fun getMedicineList() {
        viewModelScope.launch {
            _dashboardState.value = DashboardState(
                isLoading = true, medicineList = null, errorMessage = null
            )

            medicineRepository.getMedicineList().collect { result ->
                when (result) {
                    is NetworkResult.ClientError -> {
                        updateErrorMessage("Unable to Medicine List! Please Try Again.")
                    }

                    is NetworkResult.NetworkError -> {
                        updateErrorMessage("Check Internet Connection.")
                    }

                    is NetworkResult.ServerError -> {
                        updateErrorMessage("Oops! Our Server is Down.")
                    }

                    is NetworkResult.Success -> {
                        _dashboardState.value = DashboardState(
                            isLoading = false, medicineList = result.data, errorMessage = null
                        )
                    }
                }
            }
        }
    }

    private suspend fun updateErrorMessage(errorMessage: String) {
        _dashboardState.value = DashboardState(
            isLoading = false, medicineList = getCachedMedicine(), errorMessage = errorMessage
        )
    }

    fun onSearchMedicine(query: String) {
        _searchMedicine.value = query
        applySearchFilter()
    }

    private fun applySearchFilter() {
        val query = _searchMedicine.value.lowercase()
        val filteredMedicineList = _dashboardState.value.medicineList?.filter {
            it.name.lowercase().contains(query)
        }

        _dashboardState.value = DashboardState(
            isLoading = false, errorMessage = null, medicineList = filteredMedicineList
        )
    }

    private suspend fun getCachedMedicine(): List<Medicine> {
        return medicineRepository.getCachedMedicineList()
    }
}