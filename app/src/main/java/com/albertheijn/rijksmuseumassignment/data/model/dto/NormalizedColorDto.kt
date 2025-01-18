package com.albertheijn.rijksmuseumassignment.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NormalizedColorDto(
    @SerialName("originalHex")
    val originalHex: String? = null,

    @SerialName("normalizedHex")
    val normalizedHex: String? = null
)
