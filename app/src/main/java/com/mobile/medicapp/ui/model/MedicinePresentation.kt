package com.mobile.medicapp.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MedicinePresentation(
    val dose: String,
    val frequency: String,
    val name: String,
    val route: String,
    val strength: String,
    val use: String
) : Parcelable
