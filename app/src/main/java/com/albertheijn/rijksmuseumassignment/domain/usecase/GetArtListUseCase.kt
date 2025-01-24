package com.albertheijn.rijksmuseumassignment.domain.usecase

import androidx.paging.PagingData
import com.albertheijn.rijksmuseumassignment.domain.model.Art
import com.albertheijn.rijksmuseumassignment.domain.repository.RijksmuseumRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArtListUseCase @Inject constructor(
    private val repository: RijksmuseumRepository
) {
    suspend operator fun invoke(): Flow<PagingData<Art>> {
        return repository.getArtList()
    }
}
