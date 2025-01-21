package com.albertheijn.rijksmuseumassignment.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.albertheijn.rijksmuseumassignment.data.network.RijksmuseumApi
import com.albertheijn.rijksmuseumassignment.domain.mapper.toDomain
import com.albertheijn.rijksmuseumassignment.domain.model.Art
import timber.log.Timber
import java.net.SocketTimeoutException

class ArtPagingSource(
    private val rijksmuseumApi: RijksmuseumApi
) : PagingSource<Int, Art>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Art> {
        return try {
            val page = params.key ?: 0
            val getCollectionResponse = rijksmuseumApi.getCollection(page = page)
            val artItems = getCollectionResponse.artObjects?.mapNotNull { it?.toDomain() }.orEmpty()

            val nextPage = when {
                page * PAGE_SIZE >= (getCollectionResponse.count ?: 0) -> null
                else -> page + 1
            }

            val prevPage = when {
                page > 0 -> page - 1
                else -> null
            }

            LoadResult.Page(
                data = artItems,
                prevKey = prevPage,
                nextKey = nextPage
            )
        } catch (e: SocketTimeoutException) {
            Timber.e(e, "Timeout on page ${params.key}")

            LoadResult.Error(e)
        }  catch (e: Exception) {
            Timber.e(e, "Error loading page in ArtPagingSource. ${params.key}")

            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Art>): Int? {
        val anchorPosition = state.anchorPosition ?: return null

        val anchorPage = state.closestPageToPosition(anchorPosition)

        return anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
    }
}
