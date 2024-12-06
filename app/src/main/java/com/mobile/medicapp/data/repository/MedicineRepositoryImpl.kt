package com.mobile.medicapp.data.repository

import com.mobile.medicapp.data.network.model.MedicineListDTO
import com.mobile.medicapp.data.network.service.MedicineService
import com.mobile.medicapp.di.IoDispatcher
import com.mobile.medicapp.domain.repository.MedicineRepository
import com.mobile.medicapp.utils.NetworkResult
import com.mobile.medicapp.utils.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MedicineRepositoryImpl @Inject constructor(
    private val medicineService: MedicineService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : MedicineRepository {

    override suspend fun getMedicineList(): Flow<NetworkResult<MedicineListDTO>> {
        return flow {
            val result = safeApiCall {
                medicineService.getMedicineList()
            }

            emit(result)
        }.flowOn(ioDispatcher)
    }
}