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
import com.albertheijn.rijksmuseumassignment.presentation.screen.ComposableNavigationScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ArtListViewModel @Inject constructor(
    private val getArtListUseCase: GetArtListUseCase
) : ViewModel() {
    private val _groupedPagingState = MutableStateFlow<PagingData<ArtListItemUiModel>>(
        value = PagingData.empty()
    )
    val groupedPagingState: StateFlow<PagingData<ArtListItemUiModel>> = _groupedPagingState

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
                                    beforeArt?.author != afterArt.author ->
                                        ArtListItemUiModel.ArtistHeader(artist = afterArt.author)

                                    else -> null
                                }
                            }
                    }.collect { groupedPagingData ->
                    _groupedPagingState.value = groupedPagingData
                }
            } catch (e: Exception) {
                Timber.e(t = e, message = "Error fetching art collection.")
            }
        }
    }

    sealed class UIEvent {
        data object OnRefresh : UIEvent()
    }
}
