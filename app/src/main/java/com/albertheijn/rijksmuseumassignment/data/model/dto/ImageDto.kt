package com.albertheijn.rijksmuseumassignment.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageDto(
    @SerialName("guid")
    val guid: String? = null,

    @SerialName("height")
    val height: Int? = null,

    @SerialName("offsetPercentageX")
    val offsetPercentageX: Int? = null,

    @SerialName("offsetPercentageY")
    val offsetPercentageY: Int? = null,

    @SerialName("url")
    val url: String? = null,

    @SerialName("width")
    val width: Int? = null
)
