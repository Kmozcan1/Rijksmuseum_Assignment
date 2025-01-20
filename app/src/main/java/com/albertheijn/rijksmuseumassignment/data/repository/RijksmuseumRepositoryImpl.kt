package com.albertheijn.rijksmuseumassignment.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.albertheijn.rijksmuseumassignment.data.ArtPagingSource
import com.albertheijn.rijksmuseumassignment.data.network.RijksmuseumApi
import com.albertheijn.rijksmuseumassignment.domain.mapper.toDomainDetail
import com.albertheijn.rijksmuseumassignment.domain.model.Art
import com.albertheijn.rijksmuseumassignment.domain.model.ArtDetails
import com.albertheijn.rijksmuseumassignment.domain.repository.RijksmuseumRepository
import kotlinx.coroutines.flow.Flow
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

    override suspend fun getArtList(): Flow<PagingData<Art>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = { ArtPagingSource(rijksmuseumApi) }
        ).flow
    }
}
