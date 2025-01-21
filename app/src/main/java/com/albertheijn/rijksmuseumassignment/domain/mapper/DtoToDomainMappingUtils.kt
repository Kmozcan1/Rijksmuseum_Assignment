package com.albertheijn.rijksmuseumassignment.domain.mapper

import com.albertheijn.rijksmuseumassignment.data.model.dto.ArtObjectDetailDto
import com.albertheijn.rijksmuseumassignment.data.model.dto.ArtObjectListedDto
import com.albertheijn.rijksmuseumassignment.data.model.dto.ImageDto
import com.albertheijn.rijksmuseumassignment.domain.model.Art
import com.albertheijn.rijksmuseumassignment.domain.model.ArtDetails
import com.albertheijn.rijksmuseumassignment.domain.model.Image

fun ArtObjectListedDto.toDomain(): Art? {
    return Art(
        author = principalOrFirstMaker ?: return null,
        image = headerImage?.toDomain() ?: return null,
        objectNumber = objectNumber ?: return null,
        title = title ?: return null
    )
}

// TODO: will change
fun ArtObjectDetailDto.toDomainDetail(): ArtDetails? {
    return ArtDetails(
        id = id ?: return null,
        title = title ?: return null,
        author = principalOrFirstMaker ?: return null,
        imageUrl = webImage?.url ?: return null,
    )
}

fun ImageDto.toDomain(): Image? {
    if (isImageUrlValid(url) == false) {
        return null
    }

    return Image(
        height = height ?: return null,
        width = width ?: return null,
        url = url ?: return null
    )
}

fun isImageUrlValid(imageUrl: String?) = imageUrl?.contains("https")
