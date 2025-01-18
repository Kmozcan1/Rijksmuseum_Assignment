package com.albertheijn.rijksmuseumassignment.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AcquisitionDto(
    @SerialName("method")
    val method: String? = null,

    @SerialName("date")
    val date: String? = null,

    @SerialName("creditLine")
    val creditLine: String? = null
)
