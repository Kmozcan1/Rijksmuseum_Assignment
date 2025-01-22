package com.albertheijn.rijksmuseumassignment.domain.usecase

import com.albertheijn.rijksmuseumassignment.domain.model.ArtDetails
import com.albertheijn.rijksmuseumassignment.domain.repository.RijksmuseumRepository
import javax.inject.Inject

class GetArtDetailsUseCase @Inject constructor(
    private val repository: RijksmuseumRepository
) {
    suspend operator fun invoke(
        objectNumber: String
    ): ArtDetails {
        return repository.getArtDetails(objectNumber = objectNumber)
    }
}
