package com.albertheijn.rijksmuseumassignment.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ColorDto(
    @SerialName("hex")
    val hex: String? = null,

    @SerialName("percentage")
    val percentage: Int? = null
)
