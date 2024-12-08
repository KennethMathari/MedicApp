package com.mobile.medicapp.data.mapper

import com.mobile.medicapp.data.local.entity.MedicineEntity
import com.mobile.medicapp.data.network.model.Medicine

fun MedicineEntity.toMedicine(): Medicine {
    return Medicine(
        dose = this.dose,
        name = this.name,
        route = this.route,
        frequency = this.frequency,
        use = this.use,
        strength = this.strength
    )
}