package com.albertheijn.rijksmuseumassignment.domain.repository

import com.albertheijn.rijksmuseumassignment.domain.model.Art
import com.albertheijn.rijksmuseumassignment.domain.model.ArtDetails

interface RijksmuseumRepository {
    suspend fun getArtDetails(objectNumber: String): ArtDetails

    suspend fun getArtList(page: Int): List<Art>
}