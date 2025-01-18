package com.albertheijn.rijksmuseumassignment.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AcquisitionDto(
    @SerialName("creditLine")
    val creditLine: String? = null,

    @SerialName("date")
    val date: String? = null,

    @SerialName("method")
    val method: String? = null
)
