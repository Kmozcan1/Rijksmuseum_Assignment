package com.albertheijn.rijksmuseumassignment.presentation.screen.detail

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

class ArtDetailScreen : ComposableNavigationScreen() {
    override val hasBackButton = false

    override val route = "artDetail/{objectNumber}"

    @Composable
    override fun Content() {
        val viewModel: HomeViewModel = hiltViewModel()


    }

    @Composable
    private fun ArtDetails(
        lazyPagingItems: LazyPagingItems<ArtListItem>,
        appendState: LoadState
    ) {

    }

    @Composable
    override fun TitleContent() {
        Image(
            painter = painterResource(id = R.drawable.ic_rijksmuseum_topbar),
            contentDescription = "Home screen top bar logo",
            modifier = Modifier.fillMaxSize(fraction = 0.6f)
        )
    }

    @Composable
    fun ArtColumn(art: Art) {
        val maxWidth = when {
            Dimens.maxImageWidth > art.image.width.dp -> art.image.width.dp
            else -> {
                400.dp
            }
        }

        val ratio = art.image.width.toFloat() / art.image.height.toFloat()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Dimens.standardQuarterPadding),
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = maxWidth)
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
}

