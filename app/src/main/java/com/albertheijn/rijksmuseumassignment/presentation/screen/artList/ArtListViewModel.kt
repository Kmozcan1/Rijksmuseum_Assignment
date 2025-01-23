package com.albertheijn.rijksmuseumassignment.presentation.screen.artList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.albertheijn.rijksmuseumassignment.domain.usecase.GetArtListUseCase
import com.albertheijn.rijksmuseumassignment.presentation.mapper.toUiModel
import com.albertheijn.rijksmuseumassignment.presentation.model.ArtListItemUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtListViewModel @Inject constructor(
    private val getArtListUseCase: GetArtListUseCase
) : ViewModel() {
    private val _pagingDataFlow: MutableStateFlow<PagingData<ArtListItemUiModel>> =
        MutableStateFlow(value = PagingData.empty())
    val pagingDataFlow: StateFlow<PagingData<ArtListItemUiModel>> = _pagingDataFlow

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
            getArtListUseCase()
                .cachedIn(viewModelScope)
                .map { pagingData ->
                    pagingData.map { art ->
                        ArtListItemUiModel.ArtItem(art.toUiModel())
                    }.insertSeparators { before, after ->
                        val beforeArt = before?.art
                        val afterArt = after?.art ?: return@insertSeparators null

                        if (beforeArt?.author != afterArt.author) {
                            ArtListItemUiModel.ArtistHeader(artist = afterArt.author)
                        } else {
                            null
                        }
                    }
                }
                .collect { pagingData ->
                    _pagingDataFlow.value = pagingData
                }
        }
    }

    sealed class UIEvent {
        data object OnRefresh : UIEvent()
    }
}