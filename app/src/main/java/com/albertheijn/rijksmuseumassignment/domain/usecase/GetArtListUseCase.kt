package com.albertheijn.rijksmuseumassignment.domain.usecase

import com.albertheijn.rijksmuseumassignment.domain.model.Art
import com.albertheijn.rijksmuseumassignment.domain.repository.RijksmuseumRepository
import javax.inject.Inject

class GetArtListUseCase @Inject constructor(
    private val repository: RijksmuseumRepository
) {
    suspend operator fun invoke(
        page: Int
    ): List<Art> {
        return repository.getArtList(page)
    }
}
