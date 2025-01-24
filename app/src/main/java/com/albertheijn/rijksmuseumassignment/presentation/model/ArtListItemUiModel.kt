package com.albertheijn.rijksmuseumassignment.presentation.model

sealed class ArtListItemUiModel {
    data class ArtistHeader(val artist: String) : ArtListItemUiModel()

    data class ArtItem(val art: ArtUiModel) : ArtListItemUiModel()
}