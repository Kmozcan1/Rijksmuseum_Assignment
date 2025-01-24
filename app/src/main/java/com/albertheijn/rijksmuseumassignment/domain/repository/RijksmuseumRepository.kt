package com.albertheijn.rijksmuseumassignment.domain.repository

import androidx.paging.PagingData
import com.albertheijn.rijksmuseumassignment.domain.model.Art
import com.albertheijn.rijksmuseumassignment.domain.model.ArtDetails
import kotlinx.coroutines.flow.Flow

interface RijksmuseumRepository {
    suspend fun getArtDetails(objectNumber: String): Result<ArtDetails>

    suspend fun getArtList(): Flow<PagingData<Art>>
}