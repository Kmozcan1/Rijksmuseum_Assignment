package com.albertheijn.rijksmuseumassignment

import com.albertheijn.rijksmuseumassignment.domain.model.Art
import com.albertheijn.rijksmuseumassignment.domain.repository.RijksmuseumRepository
import com.albertheijn.rijksmuseumassignment.domain.usecase.GetArtListUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetArtListUseCaseTest {
    @MockK
    private lateinit var repository: RijksmuseumRepository

    private lateinit var getCollectionUseCase: GetArtListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getCollectionUseCase = GetArtListUseCase(repository)
    }

    @Test
    fun `invoke returns list of art from repository`() = runTest {
        val page = 0

        val fakeList = listOf(
            Art(
                id = "0",
                title = "Night Watch",
                author = "Rembrandt van Rijn",
                imageUrl = "someUrl",
                longTitle = "someLongTitle",
                objectNumber = "someObjectNumber"
            )
        )

        coEvery {
            repository.getArtList(page)
        } returns fakeList

        val result = getCollectionUseCase(page)

        assertEquals(fakeList, result)

        coVerify(exactly = 1) { repository.getArtList(page) }
    }
}
