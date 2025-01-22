package com.albertheijn.rijksmuseumassignment.domain.model

data class Art(
    val author: String,
    val image: ArtImage,
    val objectNumber: String,
    val title: String
)
