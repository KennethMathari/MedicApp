package com.mobile.medicapp.ui.mapper

import com.mobile.medicapp.data.network.model.Medicine
import com.mobile.medicapp.ui.model.MedicinePresentation

fun Medicine.toMedicinePresentation(): MedicinePresentation {
    return MedicinePresentation(
        dose = this.dose,
        name = this.name,
        frequency = this.frequency,
        route = this.route,
        use = this.use,
        strength = this.strength
    )
}