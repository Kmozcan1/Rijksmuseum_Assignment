package com.albertheijn.rijksmuseumassignment.data.mapper

import com.albertheijn.rijksmuseumassignment.data.model.dto.ArtObjectDetailDto
import com.albertheijn.rijksmuseumassignment.data.model.dto.ArtObjectListedDto
import com.albertheijn.rijksmuseumassignment.data.model.dto.ImageDto
import com.albertheijn.rijksmuseumassignment.domain.model.Art
import com.albertheijn.rijksmuseumassignment.domain.model.ArtDetails
import com.albertheijn.rijksmuseumassignment.domain.model.ArtImage

fun ArtObjectDetailDto.toDomainDetail(): ArtDetails? {
    return ArtDetails(
        author = principalOrFirstMaker ?: return null,
        description = plaqueDescriptionEnglish ?: description ?: return null,
        image = webImage?.toDomain() ?: return null,
        title = title ?: return null,
        presentingDate = dating?.presentingDate ?: return null
    )
}

fun ArtObjectListedDto.toDomain(): Art? {
    return Art(
        author = principalOrFirstMaker ?: return null,
        image = headerImage?.toDomain() ?: return null,
        objectNumber = objectNumber ?: return null,
        title = title ?: return null
    )
}

fun ImageDto.toDomain(): ArtImage? {
    if (isImageUrlValid(url) == false) {
        return null
    }

    return ArtImage(
        height = height ?: return null,
        width = width ?: return null,
        url = url ?: return null
    )
}

fun isImageUrlValid(imageUrl: String?) = imageUrl?.contains("https")
