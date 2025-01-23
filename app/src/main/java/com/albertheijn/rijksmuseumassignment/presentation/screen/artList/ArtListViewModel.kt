package com.albertheijn.rijksmuseumassignment.presentation.screen.artList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.albertheijn.rijksmuseumassignment.domain.model.Art
import com.albertheijn.rijksmuseumassignment.domain.usecase.GetArtListUseCase
import com.albertheijn.rijksmuseumassignment.presentation.mapper.toUiModel
import com.albertheijn.rijksmuseumassignment.presentation.model.ArtListItemUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ArtListViewModel @Inject constructor(
    private val getArtListUseCase: GetArtListUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    init {
        loadArtCollection()
    }

    fun onEvent(uiEvent: UIEvent) {
        when (uiEvent) {
            is UIEvent.OnRefresh -> loadArtCollection()
        }
    }

    private fun loadArtCollection() {
        viewModelScope.launch {
            try {
                getArtListUseCase().cachedIn(scope = viewModelScope)
                    .map { pagingData: PagingData<Art> ->
                        pagingData.map { art -> ArtListItemUiModel.ArtItem(art.toUiModel()) }
                            .insertSeparators { before, after ->
                                val beforeArt = before?.art
                                val afterArt = after?.art ?: return@insertSeparators null

                                when {
                                    // if the item a and b has different authors
                                    // put an ArtistHeader between them
                                    beforeArt?.author != afterArt.author ->
                                        ArtListItemUiModel.ArtistHeader(artist = afterArt.author)
                                    else -> null
                                }
                            }
                    }.collect { groupedPagingData ->
                        _uiState.update {
                            it.copy(pagingData = MutableStateFlow(groupedPagingData))
                        }
                    }
            } catch (e: Exception) {
                Timber.e(t = e, message = "Error fetching art collection.")
            }
        }
    }

    sealed class UIEvent {
        data object OnRefresh : UIEvent()
    }

    data class UiState(
        val pagingData: Flow<PagingData<ArtListItemUiModel>> = MutableStateFlow(PagingData.empty()),
        val error: String? = null
    )
}
