package com.albertheijn.rijksmuseumassignment

import com.albertheijn.rijksmuseumassignment.domain.repository.RijksmuseumRepository
import com.albertheijn.rijksmuseumassignment.domain.usecase.GetArtListUseCase
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before

class GetArtListUseCaseTest {
    @MockK
    private lateinit var repository: RijksmuseumRepository

    private lateinit var getCollectionUseCase: GetArtListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getCollectionUseCase = GetArtListUseCase(repository)
    }
}
