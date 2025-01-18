package com.albertheijn.rijksmuseumassignment.domain.mapper

import com.albertheijn.rijksmuseumassignment.data.model.dto.ArtObjectDetailDto
import com.albertheijn.rijksmuseumassignment.data.model.dto.ArtObjectListedDto
import com.albertheijn.rijksmuseumassignment.domain.model.Art
import com.albertheijn.rijksmuseumassignment.domain.model.ArtDetails

// TODO: Replace with actual mappers
fun ArtObjectListedDto.toDomain(): Art? {
    return Art(
        id = id ?: return null,
        title = title ?: return null,
        author = principalOrFirstMaker ?: return null,
        longTitle = longTitle ?: return null,
        imageUrl = webImage?.url ?: return null,
        objectNumber = objectNumber ?: return null
    )
}

fun ArtObjectDetailDto.toDomainDetail(): ArtDetails? {
    return ArtDetails(
        id = id ?: return null,
        title = title ?: return null,
        author = principalOrFirstMaker ?: return null,
        imageUrl = webImage?.url ?: return null,
    )
}
