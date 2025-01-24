package com.albertheijn.rijksmuseumassignment.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.albertheijn.rijksmuseumassignment.R
import com.albertheijn.rijksmuseumassignment.presentation.theme.Dimens

@Composable
fun LoadErrorColumn(errorMessage: String?, modifier: Modifier = Modifier) {
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
                id = R.string.error_during_load,
                errorMessage ?: stringResource(id = R.string.unknown_error),
            ),
            textAlign = TextAlign.Center
        )
    }
}