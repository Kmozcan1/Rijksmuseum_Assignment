package com.albertheijn.rijksmuseumassignment.data.model.response

import com.albertheijn.rijksmuseumassignment.data.model.dto.ArtObjectListedDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionResponse(
    @SerialName("artObjects")
    val artObjects: List<ArtObjectListedDto?>? = emptyList()
) : RijksmuseumApiResponse()