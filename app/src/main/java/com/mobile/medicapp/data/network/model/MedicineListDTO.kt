package com.mobile.medicapp.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MedicineListDTO(
    @SerialName("medicine")
    val medicine: List<Medicine>
)