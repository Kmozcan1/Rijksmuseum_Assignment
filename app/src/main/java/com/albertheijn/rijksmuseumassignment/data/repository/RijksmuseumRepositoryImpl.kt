package com.albertheijn.rijksmuseumassignment.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.albertheijn.rijksmuseumassignment.data.ArtPagingSource
import com.albertheijn.rijksmuseumassignment.data.PAGE_SIZE
import com.albertheijn.rijksmuseumassignment.data.network.RijksmuseumApi
import com.albertheijn.rijksmuseumassignment.domain.mapper.toDomainDetail
import com.albertheijn.rijksmuseumassignment.domain.model.Art
import com.albertheijn.rijksmuseumassignment.domain.model.ArtDetails
import com.albertheijn.rijksmuseumassignment.domain.repository.RijksmuseumRepository
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class RijksmuseumRepositoryImpl @Inject constructor(
    private val rijksmuseumApi: RijksmuseumApi
) : RijksmuseumRepository {
    override suspend fun getArtDetails(objectNumber: String): ArtDetails {
        return try {
            val detailResponse = rijksmuseumApi.getCollectionDetails(objectNumber = objectNumber)

            detailResponse.artObject?.toDomainDetail()
                ?: error("Art detail cannot be null (objectNumber=$objectNumber)")
        } catch (e: Exception) {
            Timber.e(e, "Error fetching art details for objectNumber=$objectNumber")

            throw e
        }
    }

    override suspend fun getArtList(): Flow<PagingData<Art>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            prefetchDistance = 5
        ),
        pagingSourceFactory = { ArtPagingSource(rijksmuseumApi) }
    ).flow
}
