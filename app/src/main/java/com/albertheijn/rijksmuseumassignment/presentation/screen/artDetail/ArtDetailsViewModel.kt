package com.albertheijn.rijksmuseumassignment.presentation.screen.artDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertheijn.rijksmuseumassignment.domain.usecase.GetArtDetailsUseCase
import com.albertheijn.rijksmuseumassignment.presentation.mapper.toUiModel
import com.albertheijn.rijksmuseumassignment.presentation.model.ArtDetailsUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState> = _uiState

    init {
        loadArtDetails()
    }

    fun onEvent(uiEvent: UIEvent) {
        when (uiEvent) {
            is UIEvent.OnRefresh -> {
                _uiState.value = UiState.Refresh(_uiState.value.artDetails)

                loadArtDetails()
            }
        }
    }

    private fun loadArtDetails() {
        viewModelScope.launch {
            try {
                getArtDetailsUseCase(objectNumber = artObjectNumber).let { result ->
                    when {
                        result.isSuccess -> _uiState.value =
                            UiState.Success(artDetails = result.getOrNull()?.toUiModel())
                        result.isFailure -> _uiState.value =
                            UiState.Error(message = result.exceptionOrNull()?.localizedMessage)
                    }
                }
            } catch (e: Exception) {
                Timber.e(e, "Error fetching art details.")

                UiState.Error(message = e.localizedMessage)
            }
        }
    }

    sealed class UIEvent {
        data object OnRefresh : UIEvent()
    }

    sealed class UiState(open val artDetails: ArtDetailsUiModel? = null) {
        data object Loading : UiState()

        data class Error(val message: String?) : UiState()

        data class Refresh(override val artDetails: ArtDetailsUiModel?) : UiState(artDetails)

        data class Success(override val artDetails: ArtDetailsUiModel?) : UiState()
    }
}