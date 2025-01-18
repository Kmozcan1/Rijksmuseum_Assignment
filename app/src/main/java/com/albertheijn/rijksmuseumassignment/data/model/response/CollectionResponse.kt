package com.albertheijn.rijksmuseumassignment.data.model.response

import com.albertheijn.rijksmuseumassignment.data.model.dto.ArtObjectDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionResponse(
    @SerialName("artObjects")
    val artObjects: List<ArtObjectDto?>? = emptyList()
) : RijksmuseumApiResponse()