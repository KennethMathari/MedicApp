package com.mobile.medicapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicine")
data class MedicineEntity(
    @PrimaryKey val id: String,
    val name: String,
    val dose: String,
    val strength: String,
    val frequency: String,
    val route: String,
    val use: String
)
