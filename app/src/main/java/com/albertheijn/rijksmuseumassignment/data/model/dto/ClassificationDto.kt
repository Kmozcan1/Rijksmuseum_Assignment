package com.albertheijn.rijksmuseumassignment.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClassificationDto(
    @SerialName("classificationString")
    val classificationString: List<String>? = null,

    @SerialName("iconClassDescription")
    val iconClassDescription: List<String>? = null,

    @SerialName("iconClassIdentifier")
    val iconClassIdentifier: List<String>? = null,

    @SerialName("objectCategory")
    val objectCategory: List<String>? = null,

    @SerialName("people")
    val people: List<String>? = null,

    @SerialName("periods")
    val periods: List<String>? = null
)
