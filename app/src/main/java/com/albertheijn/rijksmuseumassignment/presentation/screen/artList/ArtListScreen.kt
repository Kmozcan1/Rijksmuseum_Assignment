package com.albertheijn.rijksmuseumassignment.presentation.screen.artList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.albertheijn.rijksmuseumassignment.R
import com.albertheijn.rijksmuseumassignment.presentation.components.RijksmuseumLogoTopBarContent
import com.albertheijn.rijksmuseumassignment.presentation.components.TextHeader
import com.albertheijn.rijksmuseumassignment.presentation.components.TextPrimary
import com.albertheijn.rijksmuseumassignment.presentation.components.TextSecondary
import com.albertheijn.rijksmuseumassignment.presentation.model.ArtListItemUiModel
import com.albertheijn.rijksmuseumassignment.presentation.model.ArtUiModel
import com.albertheijn.rijksmuseumassignment.presentation.theme.Dimens

@Composable
fun ArtListScreenTopBarContent() = RijksmuseumLogoTopBarContent()

// Experimental due to PullToRefreshBox
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtListScreen(onNavigateToDetail: (route: String) -> Unit) {
    val viewModel: ArtListViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val lazyPagingItems = uiState.pagingData.collectAsLazyPagingItems()
    val appendState = lazyPagingItems.loadState.append
    val refreshState = lazyPagingItems.loadState.refresh


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        PullToRefreshBox(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(),
            isRefreshing = refreshState == LoadState.Loading,
            onRefresh = {
                viewModel.onEvent(uiEvent = ArtListViewModel.UIEvent.OnRefresh)
            }
        ) {
            ArtListScreenContentBox(
                lazyPagingItems = lazyPagingItems,
                refreshState = refreshState,
                appendState = appendState,
                onNavigateToDetail = onNavigateToDetail
            )
        }
    }
}

@Composable
private fun ArtListScreenContentBox(
    lazyPagingItems: LazyPagingItems<ArtListItemUiModel>,
    refreshState: LoadState,
    appendState: LoadState,
    onNavigateToDetail: (route: String) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (refreshState) {
            is LoadState.Error -> InitialLoadErrorColumn(
                refreshState = refreshState,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()) //needed for PullToRefreshBox to work
            )
            else -> ArtList(
                lazyPagingItems = lazyPagingItems,
                appendState = appendState,
                onNavigateToDetail = onNavigateToDetail
            )
        }
    }
}

@Composable
private fun InitialLoadErrorColumn(refreshState: LoadState.Error, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_error_24),
            contentDescription = "Initial load error icon",
            Modifier.size(size = Dimens.standardImageSize)
        )

        TextPrimary(
            text = stringResource(
                id = R.string.error_during_initial_load,
                refreshState.error.message
                    ?: stringResource(id = R.string.unknown_error),
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun ArtList(
    lazyPagingItems: LazyPagingItems<ArtListItemUiModel>,
    appendState: LoadState,
    onNavigateToDetail: (route: String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = Dimens.standardHalfPadding),
        contentPadding = PaddingValues(all = Dimens.standardPadding)
    ) {
        artItems(lazyPagingItems = lazyPagingItems, onNavigateToDetail = onNavigateToDetail)

        item {
            when (appendState) {
                is LoadState.Loading ->
                    CircularProgressIndicator(
                        Modifier.size(size = Dimens.circularProgressIndicatorSize)
                    )
                is LoadState.Error ->
                    TextPrimary(
                        text = stringResource(
                            id = R.string.error_loading_more_art_items,
                            appendState.error.message
                                ?: stringResource(id = R.string.unknown_error)
                        )
                    )

                else -> { /* Do nothing, no need to show an additional item */
                }
            }
        }
    }
}

private fun LazyListScope.artItems(
    lazyPagingItems: LazyPagingItems<ArtListItemUiModel>,
    onNavigateToDetail: ((route: String) -> Unit)? = null
) {
    items(count = lazyPagingItems.itemCount) { index ->
        when (val item = lazyPagingItems[index]) {
            is ArtListItemUiModel.ArtItem -> ArtListItemColumn(
                art = item.art,
                onArtItemClick = onNavigateToDetail
            )
            is ArtListItemUiModel.ArtistHeader -> ArtistHeaderColumn(artist = item.artist)
            null -> {}
        }
    }
}

@Composable
private fun ArtistHeaderColumn(artist: String) {
    TextHeader(
        text = artist,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(all = Dimens.standardHalfPadding),
        bold = true,
        italic = true
    )
}

@Composable
fun ArtListItemColumn(art: ArtUiModel, onArtItemClick: ((route: String) -> Unit)? = null) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = Dimens.standardQuarterPadding),
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = Dimens.standardHalfPadding)
            .then(
                if (onArtItemClick != null) {
                    Modifier.clickable {
                        onArtItemClick(art.objectNumber)
                    }
                } else {
                    Modifier
                }
            )

    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(data = art.image.url)
                .crossfade(enable = true)
                .build(),
            fallback = painterResource(id = R.drawable.ic_art_image_fallback),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(ratio = art.image.aspectRatio)
        )

        TextSecondary(
            text = art.title,
            textAlign = TextAlign.Center,
        )
    }
}

