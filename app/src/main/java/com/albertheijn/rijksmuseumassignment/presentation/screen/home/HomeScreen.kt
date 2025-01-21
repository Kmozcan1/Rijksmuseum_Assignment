package com.albertheijn.rijksmuseumassignment.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.albertheijn.rijksmuseumassignment.R
import com.albertheijn.rijksmuseumassignment.domain.model.Art
import com.albertheijn.rijksmuseumassignment.presentation.components.TextHeader
import com.albertheijn.rijksmuseumassignment.presentation.components.TextPrimary
import com.albertheijn.rijksmuseumassignment.presentation.components.TextSecondary
import com.albertheijn.rijksmuseumassignment.presentation.screen.ComposableNavigationScreen
import com.albertheijn.rijksmuseumassignment.presentation.theme.Dimens

class HomeScreen : ComposableNavigationScreen() {
    override val hasBackButton = false

    override val route = "home"

    @Composable
    override fun Content() {
        val viewModel: HomeViewModel = hiltViewModel()
        val lazyPagingItems = viewModel.groupedPagingState.collectAsLazyPagingItems()
        val appendState = lazyPagingItems.loadState.append
        val refreshState = lazyPagingItems.loadState.refresh

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            when (refreshState) {
                is LoadState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.size(size = Dimens.standardRowHeight)
                )

                is LoadState.Error -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_error_24),
                            contentDescription = "Initial load error icon",
                            Modifier.size(size = Dimens.standardImageSize)
                        )

                        TextPrimary(
                            text = "Error during initial load: ${refreshState.error.message}"
                        )
                    }
                }

                is LoadState.NotLoading -> ArtList(lazyPagingItems, appendState)
            }
        }
    }

    @Composable
    private fun ArtList(
        lazyPagingItems: LazyPagingItems<ArtListItem>,
        appendState: LoadState
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Dimens.standardHalfPadding),
            contentPadding = PaddingValues(Dimens.standardPadding)
        ) {
            artItems(lazyPagingItems, this@HomeScreen)

            item {
                when (appendState) {
                    is LoadState.Loading ->
                        CircularProgressIndicator(Modifier.size(Dimens.standardRowHeight))
                    is LoadState.Error ->
                        TextPrimary("Error loading more: ${appendState.error.message}")
                    else -> { /* Do nothing, no need to show an additional item */ }
                }
            }
        }
    }

    private fun LazyListScope.artItems(
        lazyPagingItems: LazyPagingItems<ArtListItem>,
        homeScreen: HomeScreen
    ) {
        items(count = lazyPagingItems.itemCount) { index ->
            when (val item = lazyPagingItems[index]) {
                is ArtListItem.ArtItem -> homeScreen.ArtListItemColumn(item.art)
                is ArtListItem.ArtistHeader -> ArtistHeaderColumn(item)
                null -> {}
            }
        }
    }

    @Composable
    private fun ArtistHeaderColumn(item: ArtListItem.ArtistHeader) {
        TextHeader(
            text = item.artistName,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(Dimens.standardHalfPadding),
            bold = true,
            italic = true
        )
    }

    @Composable
    fun ArtListItemColumn(art: Art) {
        val ratio = art.image.width.toFloat() / art.image.height.toFloat()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Dimens.standardQuarterPadding),
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = Dimens.standardHalfPadding)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(art.image.url)
                    .crossfade(true)
                    .build(),
                fallback = painterResource(id = R.drawable.ic_list_image_placeholder),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(ratio)
            )

            TextSecondary(
                text = art.title,
                textAlign = TextAlign.Center,
            )
        }
    }

    @Composable
    override fun TitleContent() {
        Image(
            painter = painterResource(id = R.drawable.ic_rijksmuseum_topbar),
            contentDescription = "Home screen top bar logo",
            modifier = Modifier.fillMaxSize(fraction = 0.6f)
        )
    }
}

