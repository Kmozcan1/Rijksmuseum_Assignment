package com.albertheijn.rijksmuseumassignment.presentation.mapper

import com.albertheijn.rijksmuseumassignment.domain.model.Art
import com.albertheijn.rijksmuseumassignment.domain.model.ArtDetails
import com.albertheijn.rijksmuseumassignment.domain.model.ArtImage
import com.albertheijn.rijksmuseumassignment.presentation.model.ArtDetailsUiModel
import com.albertheijn.rijksmuseumassignment.presentation.model.ArtImageUiModel
import com.albertheijn.rijksmuseumassignment.presentation.model.ArtUiModel

fun Art.toUiModel(): ArtUiModel = ArtUiModel(
    author = author,
    image = image.toUiModel(),
    objectNumber = objectNumber,
    title = title
)

fun ArtDetails.toUiModel() = ArtDetailsUiModel(
    author = author,
    description = description,
    image = image.toUiModel(),
    title = title,
    date = presentingDate,
)

fun ArtImage.toUiModel() = ArtImageUiModel(
    aspectRatio = width.toFloat() / height.toFloat(),
    url = url
)