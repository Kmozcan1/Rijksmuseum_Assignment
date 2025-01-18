package com.albertheijn.rijksmuseumassignment.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LabelDto(
    @SerialName("date")
    val date: String? = null,

    @SerialName("description")
    val description: String? = null,

    @SerialName("makerLine")
    val makerLine: String? = null,

    @SerialName("notes")
    val notes: String? = null,

    @SerialName("title")
    val title: String? = null

)
