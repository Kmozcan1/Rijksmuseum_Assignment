package com.albertheijn.rijksmuseumassignment.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.albertheijn.rijksmuseumassignment.presentation.components.FlowTopBar
import com.albertheijn.rijksmuseumassignment.presentation.components.TopBarScreen
import com.albertheijn.rijksmuseumassignment.presentation.screen.home.HomeScreen
import com.albertheijn.rijksmuseumassignment.presentation.util.slidingNavigationEnterAnimation
import com.albertheijn.rijksmuseumassignment.presentation.util.slidingNavigationExitAnimation
import com.albertheijn.rijksmuseumassignment.presentation.util.slidingNavigationPopEnterAnimation
import com.albertheijn.rijksmuseumassignment.presentation.util.slidingNavigationPopExitAnimation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState = savedInstanceState)

        enableEdgeToEdge()

        setContent {
            Content()
        }
    }
}

@Composable
fun Content() {
    val navController = rememberNavController()

    val viewModel: MainViewModel = hiltViewModel()

    val uiState = viewModel.uiState.collectAsState().value

    TopBarScreen(
        topBar = {
            FlowTopBar(navController = navController) {
                uiState.currentScreen.value?.TitleContent()
            }
        },
    ) {
        NavHost(
            navController = navController,
            startDestination = HomeScreen().route,
            enterTransition = slidingNavigationEnterAnimation(),
            exitTransition = slidingNavigationExitAnimation(),
            popEnterTransition = slidingNavigationPopEnterAnimation(),
            popExitTransition = slidingNavigationPopExitAnimation(),
        ) {
            HomeScreen().let { homeScreen ->
                composable(route = homeScreen.route) {
                    viewModel.onEvent(uiEvent = MainViewModel.UIEvent.OnScreenLaunched(homeScreen))

                    homeScreen.Content()
                }
            }
        }
    }
}
