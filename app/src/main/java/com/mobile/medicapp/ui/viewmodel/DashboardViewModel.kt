package com.mobile.medicapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
                        updateErrorMessage("Unable to Medicine list! Check Internet Connection.")
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

    private fun updateErrorMessage(errorMessage: String) {
        _dashboardState.value = DashboardState(
            isLoading = false, medicineList = null, errorMessage = errorMessage
        )
    }
}