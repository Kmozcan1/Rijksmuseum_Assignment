package com.albertheijn.rijksmuseumassignment.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinksDto(
    @SerialName("search")
    val search: String? = null,

    @SerialName("self")
    val self: String? = null,

    @SerialName("web")
    val web: String? = null
)
