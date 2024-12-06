package com.mobile.medicapp.domain.repository

import com.mobile.medicapp.data.network.model.MedicineListDTO
import com.mobile.medicapp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface MedicineRepository {

    suspend fun getMedicineList(): Flow<NetworkResult<MedicineListDTO>>
}