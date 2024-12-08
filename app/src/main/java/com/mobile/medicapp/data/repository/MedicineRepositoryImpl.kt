package com.mobile.medicapp.data.repository

import com.mobile.medicapp.data.local.dao.MedicineDao
import com.mobile.medicapp.data.mapper.toMedicine
import com.mobile.medicapp.data.mapper.toMedicineEntity
import com.mobile.medicapp.data.network.model.Medicine
import com.mobile.medicapp.data.network.service.MedicineService
import com.mobile.medicapp.di.IoDispatcher
import com.mobile.medicapp.domain.repository.MedicineRepository
import com.mobile.medicapp.utils.NetworkResult
import com.mobile.medicapp.utils.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MedicineRepositoryImpl @Inject constructor(
    private val medicineService: MedicineService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val medicineDao: MedicineDao
) : MedicineRepository {

    override suspend fun getMedicineList(): Flow<NetworkResult<List<Medicine>>> {
        return flow {
            val cachedMedicines = medicineDao.getAllMedicines().map {
                it.toMedicine()
            }

            if (cachedMedicines.isNotEmpty()) {
                emit(NetworkResult.Success(cachedMedicines))
            }

            val result = safeApiCall { medicineService.getMedicineList().medicine }

            if (result is NetworkResult.Success) {
                // Cache the fetched data
                val medicineEntities = result.data.map { it.toMedicineEntity() }
                medicineDao.deleteAllMedicines()
                medicineDao.insertAll(medicineEntities)
            }

            emit(result)
        }.flowOn(ioDispatcher)
    }

    override suspend fun getCachedMedicineList(): List<Medicine> {
        return withContext(ioDispatcher) {
            val cachedMedicines = medicineDao.getAllMedicines().map {
                it.toMedicine()
            }
            cachedMedicines.ifEmpty { emptyList() }
        }
    }
}