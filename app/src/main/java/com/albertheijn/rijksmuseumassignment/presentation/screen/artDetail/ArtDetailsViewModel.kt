package com.albertheijn.rijksmuseumassignment.presentation.screen.artDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertheijn.rijksmuseumassignment.domain.usecase.GetArtDetailsUseCase
import com.albertheijn.rijksmuseumassignment.presentation.mapper.toUiModel
import com.albertheijn.rijksmuseumassignment.presentation.model.ArtDetailsUiModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ArtDetailsViewModel @Inject constructor(
    private val getArtDetailsUseCase: GetArtDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val artObjectNumber: String = savedStateHandle["artObjectNumber"]
        ?: throw IllegalArgumentException("artObjectNumber is required")

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    init {
        loadArtDetails()
    }

    private fun loadArtDetails() {
        viewModelScope.launch {
            try {
                val artDetails = getArtDetailsUseCase(objectNumber = artObjectNumber).toUiModel()

                _uiState.update {
                    it.copy(artDetails = artDetails, isLoading = false)
                }
            } catch (e: Exception) {
                Timber.e(e, "Error fetching art details.")

                _uiState.update {
                    it.copy(error = e.localizedMessage, isLoading = false)
                }
            }
        }
    }

    data class UiState(
        val artDetails: ArtDetailsUiModel? = null,
        val error: String? = null,
        val isLoading: Boolean = true
    )
}