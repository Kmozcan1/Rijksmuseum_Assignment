package com.albertheijn.rijksmuseumassignment.presentation.screen

import androidx.compose.runtime.Composable
import com.albertheijn.rijksmuseumassignment.presentation.screen.artDetail.ArtDetailsScreen
import com.albertheijn.rijksmuseumassignment.presentation.screen.artDetail.ArtDetailsScreenTitle
import com.albertheijn.rijksmuseumassignment.presentation.screen.artList.HomeScreen
import com.albertheijn.rijksmuseumassignment.presentation.screen.artList.ArtListScreenTopBarTitle

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
            ArtDetailsScreenTitle()
        }
    }

    object ArtList : ComposableNavigationScreen() {
        override val hasBackButton = false

        override val route = "artList"

        @Composable
        fun Content(onNavigateToDetail: (route: String) -> Unit) {
            HomeScreen(onNavigateToDetail = onNavigateToDetail)
        }

        @Composable
        override fun TitleContent() {
            ArtListScreenTopBarTitle()
        }
    }

}