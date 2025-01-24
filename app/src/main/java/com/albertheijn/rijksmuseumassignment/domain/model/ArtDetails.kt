package com.albertheijn.rijksmuseumassignment.domain.model

data class ArtDetails(
    val author: String,
    val description: String,
    val image: ArtImage,
    val title: String,
    val presentingDate: String
)
