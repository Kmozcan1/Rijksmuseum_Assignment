package com.albertheijn.rijksmuseumassignment.data.repository

import com.albertheijn.rijksmuseumassignment.data.network.RijksmuseumApi
import com.albertheijn.rijksmuseumassignment.domain.mapper.toDomain
import com.albertheijn.rijksmuseumassignment.domain.mapper.toDomainDetail
import com.albertheijn.rijksmuseumassignment.domain.model.Art
import com.albertheijn.rijksmuseumassignment.domain.model.ArtDetails
import com.albertheijn.rijksmuseumassignment.domain.repository.RijksmuseumRepository
import javax.inject.Inject

class RijksmuseumRepositoryImpl @Inject constructor(
    private val rijksmuseumApi: RijksmuseumApi
) : RijksmuseumRepository {
    override suspend fun getArtDetails(objectNumber: String): ArtDetails {
        val detailResponse = rijksmuseumApi.getCollectionDetails(
            objectNumber = objectNumber
        )

        // TODO: handle this properly
        return detailResponse.artObject?.toDomainDetail()
            ?: error(message = "Art detail cannot be null")
    }

    override suspend fun getArtList(
        page: Int
    ): List<Art> {
        val responseDto = rijksmuseumApi.getCollection(page = page)

        return responseDto.artObjects
            ?.mapNotNull { it?.toDomain() }
            .orEmpty()
    }
}
