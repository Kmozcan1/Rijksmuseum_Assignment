package com.albertheijn.rijksmuseumassignment.presentation.mapper

import androidx.paging.PagingData
import androidx.paging.insertSeparators
import androidx.paging.map
import com.albertheijn.rijksmuseumassignment.domain.model.Art
import com.albertheijn.rijksmuseumassignment.domain.model.ArtDetails
import com.albertheijn.rijksmuseumassignment.domain.model.ArtImage
import com.albertheijn.rijksmuseumassignment.presentation.model.ArtDetailsUiModel
import com.albertheijn.rijksmuseumassignment.presentation.model.ArtImageUiModel
import com.albertheijn.rijksmuseumassignment.presentation.model.ArtListItemUiModel
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

fun PagingData<Art>.toArtListItemUiModel(): PagingData<ArtListItemUiModel> =
    map { art ->
        ArtListItemUiModel.ArtItem(art.toUiModel())
    }.insertSeparators { before, after ->
        val afterArt = after?.art ?: return@insertSeparators null

        val beforeArt = before?.art ?: return@insertSeparators ArtListItemUiModel.ArtistHeader(
            artist = afterArt.author
        )

        if (beforeArt.author != afterArt.author) {
            ArtListItemUiModel.ArtistHeader(artist = afterArt.author)
        } else {
            null
        }
    }