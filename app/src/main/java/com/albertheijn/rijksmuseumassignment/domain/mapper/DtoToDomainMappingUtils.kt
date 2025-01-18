package com.albertheijn.rijksmuseumassignment.domain.mapper

import com.albertheijn.rijksmuseumassignment.data.model.dto.ArtObjectDto
import com.albertheijn.rijksmuseumassignment.domain.model.Art
import com.albertheijn.rijksmuseumassignment.domain.model.ArtDetail

// TODO: Replace with actual mappers
fun ArtObjectDto.toDomain(): Art {
    return Art(
        id = id.orEmpty(),
        title = title.orEmpty(),
        author = principalOrFirstMaker.orEmpty(),
        imageUrl = webImage?.url
    )
}

fun ArtObjectDto.toDomainDetail(): ArtDetail {
    return ArtDetail(
        id = id.orEmpty(),
        title = title.orEmpty(),
        author = principalOrFirstMaker.orEmpty(),
        imageUrl = webImage?.url
    )
}
