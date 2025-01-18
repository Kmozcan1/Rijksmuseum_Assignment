package com.albertheijn.rijksmuseumassignment.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class RijksmuseumApiResponse(
    @SerialName("count")
    val count: Int? = 0,

    @SerialName("elapsedMilliseconds")
    val elapsedMilliseconds: Long? = null
)