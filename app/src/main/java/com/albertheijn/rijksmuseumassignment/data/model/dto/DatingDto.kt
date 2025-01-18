package com.albertheijn.rijksmuseumassignment.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DatingDto(
    @SerialName("presentingDate")
    val presentingDate: String? = null,

    @SerialName("sortingDate")
    val sortingDate: Int? = null,

    @SerialName("period")
    val period: Int? = null,

    @SerialName("yearEarly")
    val yearEarly: Int? = null,

    @SerialName("yearLate")
    val yearLate: Int? = null
)
