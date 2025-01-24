package com.albertheijn.rijksmuseumassignment.presentation.screen

import androidx.compose.runtime.Composable

abstract class ComposableNavigationScreen {
    abstract val hasBackButton: Boolean

    abstract val route: String

    @Composable
    abstract fun TitleContent()
}