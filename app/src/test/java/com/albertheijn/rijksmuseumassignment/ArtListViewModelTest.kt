package com.albertheijn.rijksmuseumassignment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import com.albertheijn.rijksmuseumassignment.domain.model.Art
import com.albertheijn.rijksmuseumassignment.domain.model.ArtImage
import com.albertheijn.rijksmuseumassignment.domain.usecase.GetArtListUseCase
import com.albertheijn.rijksmuseumassignment.presentation.mapper.toArtListItemUiModel
import com.albertheijn.rijksmuseumassignment.presentation.model.ArtListItemUiModel
import com.albertheijn.rijksmuseumassignment.presentation.screen.artList.ArtListViewModel
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ArtListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private val mockUseCase: GetArtListUseCase = mockk()
    private lateinit var viewModel: ArtListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher = testDispatcher)
        viewModel = ArtListViewModel(getArtListUseCase = mockUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `verify paging data is mapped to ArtListItemUiModel correctly`() = runBlocking {
        val mockArtList = listOf(
            Art(
                author = "Van Gogh",
                title = "Starry Night",
                objectNumber = "1",
                image = ArtImage(height = 0, width = 0, url = "")
            ),
            Art(
                author = "Picasso",
                title = "Guernica",
                objectNumber = "2",
                image = ArtImage(height = 0, width = 0, url = "")
            ),
        )
        val pagingData = PagingData.from(data = mockArtList)
        val artListItems = flowOf(value = pagingData.toArtListItemUiModel()).asSnapshot()

        assertEquals(4, artListItems.size)
        assertEquals("Van Gogh", (artListItems[0] as ArtListItemUiModel.ArtistHeader).artist)
        assertEquals("Starry Night", (artListItems[1] as ArtListItemUiModel.ArtItem).art.title)
        assertEquals("Picasso", (artListItems[2] as ArtListItemUiModel.ArtistHeader).artist)
        assertEquals("Guernica", (artListItems[3] as ArtListItemUiModel.ArtItem).art.title)
    }
}
