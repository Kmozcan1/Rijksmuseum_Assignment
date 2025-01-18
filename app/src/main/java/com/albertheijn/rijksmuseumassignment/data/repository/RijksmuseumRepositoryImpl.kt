package com.albertheijn.rijksmuseumassignment.data.repository

import com.albertheijn.rijksmuseumassignment.data.network.RijksmuseumApi
import com.albertheijn.rijksmuseumassignment.domain.mapper.toDomain
import com.albertheijn.rijksmuseumassignment.domain.mapper.toDomainDetail
import com.albertheijn.rijksmuseumassignment.domain.model.Art
import com.albertheijn.rijksmuseumassignment.domain.model.ArtDetail
import com.albertheijn.rijksmuseumassignment.domain.repository.RijksmuseumRepository
import javax.inject.Inject

class RijksmuseumRepositoryImpl @Inject constructor(
    private val rijksmuseumApi: RijksmuseumApi
) : RijksmuseumRepository {
    override suspend fun getCollection(
        query: String?,
        page: Int,
        pageSize: Int
    ): List<Art> {
        val responseDto = rijksmuseumApi.getCollection(
            query = query,
            page = page,
            pageSize = pageSize
        )

        return responseDto.artObjects
            ?.mapNotNull { it?.toDomain() }
            .orEmpty()
    }

    override suspend fun getArtDetail(objectNumber: String): ArtDetail {
        val detailResponse = rijksmuseumApi.getArtDetail(
            apiKey = "MY_API_KEY",
            objectNumber = objectNumber
        )

        // TODO: handle this properly
        return detailResponse.artObject?.toDomainDetail()
            ?: error(message = "Art detail cannot be null")
    }
}
