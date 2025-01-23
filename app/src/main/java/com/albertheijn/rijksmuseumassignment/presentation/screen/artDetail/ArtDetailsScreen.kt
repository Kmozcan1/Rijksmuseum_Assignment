package com.albertheijn.rijksmuseumassignment.presentation.screen.artDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.albertheijn.rijksmuseumassignment.R
import com.albertheijn.rijksmuseumassignment.presentation.components.LoadErrorColumn
import com.albertheijn.rijksmuseumassignment.presentation.components.RijksmuseumLogoTopBarContent
import com.albertheijn.rijksmuseumassignment.presentation.components.TextHeader
import com.albertheijn.rijksmuseumassignment.presentation.components.TextPrimary
import com.albertheijn.rijksmuseumassignment.presentation.model.ArtDetailsUiModel
import com.albertheijn.rijksmuseumassignment.presentation.model.ArtImageUiModel
import com.albertheijn.rijksmuseumassignment.presentation.theme.Dimens.circularProgressIndicatorSize
import com.albertheijn.rijksmuseumassignment.presentation.theme.Dimens.standardHalfPadding
import com.albertheijn.rijksmuseumassignment.presentation.theme.Dimens.standardPadding
import com.albertheijn.rijksmuseumassignment.presentation.theme.Dimens.standardQuarterPadding

// Experimental due to PullToRefreshBox
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtDetailsScreen() {
    val viewModel: ArtDetailsViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    PullToRefreshBox(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize(),
        isRefreshing = uiState is ArtDetailsViewModel.UiState.Refresh,
        onRefresh = { viewModel.onEvent(uiEvent = ArtDetailsViewModel.UIEvent.OnRefresh) }
    ) {
        ArtDetailsContent(uiState = uiState)
    }
}

@Composable
fun ArtDetailsScreenTopBarContent() = RijksmuseumLogoTopBarContent()

@Composable
private fun ArtAuthorRow(author: String) {
    Row(horizontalArrangement = Arrangement.spacedBy(space = standardQuarterPadding)) {
        TextPrimary(text = stringResource(R.string.by_colon), bold = true)

        TextPrimary(text = author, textAlign = TextAlign.End)
    }
}

@Composable
private fun ArtDateRow(date: String) {
    Row(horizontalArrangement = Arrangement.spacedBy(space = standardQuarterPadding)) {
        TextPrimary(text = stringResource(R.string.date_colon), bold = true)

        TextPrimary(text = date, textAlign = TextAlign.End)
    }
}

@Composable
private fun ArtDescriptionRow(description: String) {
    TextPrimary(text = description, italic = true)
}

@Composable
private fun ArtDetailsColumn(artDetails: ArtDetailsUiModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = standardHalfPadding)
    ) {
        ArtImage(image = artDetails.image)

        Column(
            verticalArrangement = Arrangement.spacedBy(space = standardHalfPadding),
            modifier = Modifier.padding(
                all = standardPadding
            )
        ) {
            ArtTitle(title = artDetails.title)

            ArtDateRow(date = artDetails.date)

            ArtAuthorRow(author = artDetails.author)

            ArtDescriptionRow(description = artDetails.description)
        }
    }
}

@Composable
private fun BoxScope.ArtDetailsContent(
    uiState: ArtDetailsViewModel.UiState
) {
    when (uiState) {
        is ArtDetailsViewModel.UiState.Error -> LoadErrorColumn(
            errorMessage = uiState.message,
            modifier = Modifier.align(alignment = Alignment.Center)
        )

        is ArtDetailsViewModel.UiState.Loading ->
            CircularProgressIndicator(
                modifier = Modifier
                    .size(size = circularProgressIndicatorSize)
                    .align(alignment = Alignment.Center)
            )

        is ArtDetailsViewModel.UiState.Success ->
            uiState.artDetails?.let { ArtDetailsColumn(artDetails = it) }

        is ArtDetailsViewModel.UiState.Refresh ->
            uiState.artDetails?.let { ArtDetailsColumn(artDetails = it) }

    }
}

@Composable
private fun ArtImage(image: ArtImageUiModel) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(data = image.url)
            .crossfade(enable = true)
            .build(),
        fallback = painterResource(id = R.drawable.ic_art_image_fallback),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(ratio = image.aspectRatio)
    )
}

@Composable
private fun ArtTitle(title: String) {
    TextHeader(text = title, bold = true)
}
