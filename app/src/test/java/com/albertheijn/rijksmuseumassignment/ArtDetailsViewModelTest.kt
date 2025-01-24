package com.albertheijn.rijksmuseumassignment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.albertheijn.rijksmuseumassignment.domain.model.ArtDetails
import com.albertheijn.rijksmuseumassignment.domain.model.ArtImage
import com.albertheijn.rijksmuseumassignment.domain.usecase.GetArtDetailsUseCase
import com.albertheijn.rijksmuseumassignment.presentation.screen.artDetail.ArtDetailsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ArtDetailsViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()
    private val getArtDetailsUseCase: GetArtDetailsUseCase = mockk()
    private lateinit var viewModel: ArtDetailsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher = testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test successful data fetch`() = runTest {
        val mockArtDetails = ArtDetails(
            author = "Vincent van Gogh",
            description = "A beautiful painting",
            image = ArtImage(height = 1000, width = 800, url = "https://mockurl.com/image.jpg"),
            title = "Starry Night",
            presentingDate = "1889"
        )

        coEvery { getArtDetailsUseCase("mockArtObjectNumber") } coAnswers {
            delay(1)
            Result.success(mockArtDetails)
        }


        viewModel = ArtDetailsViewModel(
            getArtDetailsUseCase = getArtDetailsUseCase,
            savedStateHandle = savedStateHandle("mockArtObjectNumber")
        )

        viewModel.uiState.test {
            assert(awaitItem() is ArtDetailsViewModel.UiState.Loading)
            advanceUntilIdle()
            assert(awaitItem() is ArtDetailsViewModel.UiState.Success)
        }
    }


    @Test
    fun `test error state`() = runTest {
        val errorMessage = "Network Error"

        coEvery { getArtDetailsUseCase("mockArtObjectNumber") } coAnswers {
            delay(1)
            Result.failure(RuntimeException(errorMessage))
        }

        viewModel = ArtDetailsViewModel(
            getArtDetailsUseCase = getArtDetailsUseCase,
            savedStateHandle = savedStateHandle("mockArtObjectNumber")
        )

        viewModel.uiState.test {
            assertTrue(awaitItem() is ArtDetailsViewModel.UiState.Loading)

            val errorState = awaitItem()
            assertTrue(errorState is ArtDetailsViewModel.UiState.Error)
            assertEquals(errorMessage, (errorState as ArtDetailsViewModel.UiState.Error).message)
        }
    }

    private fun savedStateHandle(artObjectNumber: String) =
        SavedStateHandle(mapOf("artObjectNumber" to artObjectNumber))
}
