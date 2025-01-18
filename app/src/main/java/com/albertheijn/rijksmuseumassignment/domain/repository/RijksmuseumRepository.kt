package com.albertheijn.rijksmuseumassignment.domain.repository

import com.albertheijn.rijksmuseumassignment.domain.model.Art
import com.albertheijn.rijksmuseumassignment.domain.model.ArtDetail

interface RijksmuseumRepository {
    suspend fun getArtDetail(objectNumber: String): ArtDetail

    suspend fun getCollection(query: String?, page: Int, pageSize: Int): List<Art>
}