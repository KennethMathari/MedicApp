package com.mobile.medicapp.data.network.service

import com.mobile.medicapp.data.network.model.MedicineListDTO
import retrofit2.http.GET

interface MedicineService {

    @GET("147f90e2-95cd-4d6a-8e15-ae13a0f9ad9c")
    suspend fun getMedicineList(): MedicineListDTO
}