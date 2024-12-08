package com.mobile.medicapp.data.repository

import com.mobile.medicapp.TestData.medicine
import com.mobile.medicapp.data.network.model.Medicine
import com.mobile.medicapp.domain.repository.MedicineRepository
import com.mobile.medicapp.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class MedicineRepositoryTest {
    private val medicineRepository: MedicineRepository = mockk()

    @Test
    fun getMedicineListReturnsSuccess() = runTest {
        coEvery {
            medicineRepository.getMedicineList()
        } returns flowOf(NetworkResult.Success(listOf(medicine)))

        val result = medicineRepository.getMedicineList().first()

        assertEquals(NetworkResult.Success(listOf(medicine)), result)
    }

    @Test
    fun getMedicineListReturnsNetworkError() = runTest {
        val expectedException = Exception("Error getting medicine list")

        coEvery {
            medicineRepository.getMedicineList()
        } returns flowOf(NetworkResult.NetworkError(expectedException))

        val result = medicineRepository.getMedicineList().first()

        assertEquals(NetworkResult.NetworkError(expectedException), result)
    }

    @Test
    fun getCachedMedicineListReturnsList() = runTest {

        coEvery {
            medicineRepository.getCachedMedicineList()
        } returns listOf(medicine)

        val result = medicineRepository.getCachedMedicineList()

        assertEquals(listOf(medicine), result)
    }

    @Test
    fun getCachedMedicineListReturnsEmptyList() = runTest {

        val medicineList = emptyList<Medicine>()

        coEvery {
            medicineRepository.getCachedMedicineList()
        } returns medicineList

        val result = medicineRepository.getCachedMedicineList()

        assertEquals(medicineList, result)
    }
}