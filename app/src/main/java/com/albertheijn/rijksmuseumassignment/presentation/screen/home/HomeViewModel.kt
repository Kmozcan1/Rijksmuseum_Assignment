package com.albertheijn.rijksmuseumassignment.presentation.screen.home

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
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getArtListUseCase: GetArtListUseCase
) : ViewModel() {
    private val _groupedPagingState = MutableStateFlow<PagingData<ArtListItem>>(PagingData.empty())
    val groupedPagingState: StateFlow<PagingData<ArtListItem>> = _groupedPagingState

    init {
        loadArtCollection()
    }

    private fun loadArtCollection() {
        viewModelScope.launch {
            try {
                getArtListUseCase().cachedIn(viewModelScope).map { pagingData: PagingData<Art> ->
                        pagingData.map { art -> ArtListItem.ArtItem(art) }
                            .insertSeparators { before, after ->
                                val beforeArt = before?.art
                                val afterArt = after?.art ?: return@insertSeparators null

                                when {
                                    beforeArt?.author != afterArt.author ->
                                        ArtListItem.ArtistHeader(artistName = afterArt.author)
                                    else -> null
                                }
                            }
                    }.collect { groupedPagingData ->
                        _groupedPagingState.value = groupedPagingData
                    }
            } catch (e: Exception) {
                Timber.e(e, "Error fetching art collection.")
            }
        }
    }
}

sealed class ArtListItem {
    data class ArtistHeader(val artistName: String) : ArtListItem()

    data class ArtItem(val art: Art) : ArtListItem()
}
