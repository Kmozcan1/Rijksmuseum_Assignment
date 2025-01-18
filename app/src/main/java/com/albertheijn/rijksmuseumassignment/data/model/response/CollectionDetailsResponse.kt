package com.albertheijn.rijksmuseumassignment.data.model.response

import com.albertheijn.rijksmuseumassignment.data.model.dto.ArtObjectDetailDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionDetailsResponse(
    @SerialName("artObjects")
    val artObject: ArtObjectDetailDto? = null
) : RijksmuseumApiResponse()