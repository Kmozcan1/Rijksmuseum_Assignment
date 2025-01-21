package com.albertheijn.rijksmuseumassignment.presentation.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.albertheijn.rijksmuseumassignment.domain.model.Art
import com.albertheijn.rijksmuseumassignment.domain.usecase.GetArtListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getArtListUseCase: GetArtListUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    init {
        loadArtDetail()
    }

    private fun loadArtDetail() {
        viewModelScope.launch {

        }
    }

    data class UiState(
        val isLoading: Boolean = true,
        val error: String? = null,
    )
}

sealed class ArtListItem {
    data class ArtistHeader(val artistName: String) : ArtListItem()
    data class ArtItem(val art: Art) : ArtListItem()
}
