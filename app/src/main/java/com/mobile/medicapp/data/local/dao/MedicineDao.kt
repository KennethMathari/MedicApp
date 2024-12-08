package com.mobile.medicapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mobile.medicapp.data.local.entity.MedicineEntity

@Dao
interface MedicineDao {
    @Upsert
    suspend fun insertAll(medicines: List<MedicineEntity>)

    @Query("SELECT * FROM medicine")
    suspend fun getAllMedicines(): List<MedicineEntity>
}
