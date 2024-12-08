package com.mobile.medicapp.data.mapper

import com.mobile.medicapp.data.local.entity.MedicineEntity
import com.mobile.medicapp.data.network.model.Medicine
import java.util.UUID

fun Medicine.toMedicineEntity(): MedicineEntity {
    return MedicineEntity(
        id = UUID.randomUUID().toString(),
        dose = this.dose,
        name = this.name,
        route = this.route,
        frequency = this.frequency,
        use = this.use,
        strength = this.strength
    )
}