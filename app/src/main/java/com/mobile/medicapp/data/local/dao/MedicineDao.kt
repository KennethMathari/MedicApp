package com.mobile.medicapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobile.medicapp.data.local.entity.MedicineEntity

@Dao
interface MedicineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(medicines: List<MedicineEntity>)

    @Query("SELECT * FROM medicine")
    suspend fun getAllMedicines(): List<MedicineEntity>

    @Query("DELETE FROM medicine")
    suspend fun deleteAllMedicines()
}
