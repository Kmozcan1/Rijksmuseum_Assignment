package com.albertheijn.rijksmuseumassignment.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DimensionDto(
    @SerialName("part")
    val part: String? = null,

    @SerialName("precision")
    val precision: String? = null,

    @SerialName("type")
    val type: String? = null,

    @SerialName("unit")
    val unit: String? = null,

    @SerialName("value")
    val value: String? = null
)
