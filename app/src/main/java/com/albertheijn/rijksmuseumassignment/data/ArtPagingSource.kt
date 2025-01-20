package com.albertheijn.rijksmuseumassignment.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.albertheijn.rijksmuseumassignment.data.network.RijksmuseumApi
import com.albertheijn.rijksmuseumassignment.domain.mapper.toDomain
import com.albertheijn.rijksmuseumassignment.domain.model.Art

class ArtPagingSource(
    private val rijksmuseumApi: RijksmuseumApi
) : PagingSource<Int, Art>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Art> {
        return try {
            val page = params.key ?: 0
            val artDtoList = rijksmuseumApi.getCollection(page = page).artObjects
            val artItems = artDtoList?.mapNotNull { it?.toDomain() }.orEmpty()

            val nextPage = when {
                artItems.isEmpty() -> null
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
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Art>): Int? {
        val anchorPosition = state.anchorPosition ?: return null

        val anchorPage = state.closestPageToPosition(anchorPosition)

        return anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
    }
}
