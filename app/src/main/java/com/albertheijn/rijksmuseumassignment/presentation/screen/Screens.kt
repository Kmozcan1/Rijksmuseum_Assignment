package com.albertheijn.rijksmuseumassignment.presentation.screen

import androidx.compose.runtime.Composable
import com.albertheijn.rijksmuseumassignment.presentation.screen.artDetail.ArtDetailsScreen
import com.albertheijn.rijksmuseumassignment.presentation.screen.artDetail.ArtDetailsScreenTopBarContent
import com.albertheijn.rijksmuseumassignment.presentation.screen.artList.ArtListScreen
import com.albertheijn.rijksmuseumassignment.presentation.screen.artList.ArtListScreenTopBarContent

interface Screens {
    object ArtDetail : ComposableNavigationScreen() {
        override val hasBackButton = true

        override val route = "artDetails/{artObjectNumber}"

        fun createRoute(objectNumber: String) = "artDetails/$objectNumber"

        @Composable
        fun Content() {
            ArtDetailsScreen()
        }

        @Composable
        override fun TitleContent() {
            ArtDetailsScreenTopBarContent()
        }
    }

    object ArtList : ComposableNavigationScreen() {
        override val hasBackButton = false

        override val route = "artList"

        @Composable
        fun Content(onNavigateToDetail: (route: String) -> Unit) {
            ArtListScreen(onNavigateToDetail = onNavigateToDetail)
        }

        @Composable
        override fun TitleContent() {
            ArtListScreenTopBarContent()
        }
    }

}