package com.mobile.medicapp.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Medicine(
    @SerialName("dose")
    val dose: String,
    @SerialName("frequency")
    val frequency: String,
    @SerialName("name")
    val name: String,
    @SerialName("route")
    val route: String,
    @SerialName("strength")
    val strength: String,
    @SerialName("use")
    val use: String
)