package com.albertheijn.rijksmuseumassignment.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.albertheijn.rijksmuseumassignment.presentation.components.NavigationTopBar
import com.albertheijn.rijksmuseumassignment.presentation.components.TopBarScreen
import com.albertheijn.rijksmuseumassignment.presentation.screen.Screens
import com.albertheijn.rijksmuseumassignment.presentation.util.slidingNavigationEnterAnimation
import com.albertheijn.rijksmuseumassignment.presentation.util.slidingNavigationExitAnimation
import com.albertheijn.rijksmuseumassignment.presentation.util.slidingNavigationPopEnterAnimation
import com.albertheijn.rijksmuseumassignment.presentation.util.slidingNavigationPopExitAnimation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition {
            // Example: Wait until initialization is complete
            mainViewModel.uiState.value.shouldShowSplashScreen.value
        }

        enableEdgeToEdge()

        super.onCreate(savedInstanceState = savedInstanceState)

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
    val currentScreen = uiState.currentScreen.value

    TopBarScreen(
        topBar = {
            NavigationTopBar(
                navController = navController,
                hasBackIcon = currentScreen?.hasBackButton ?: false
            ) {
                uiState.currentScreen.value?.TitleContent()
            }
        },
    ) {
        NavHost(
            navController = navController,
            startDestination = Screens.ArtList.route,
            enterTransition = slidingNavigationEnterAnimation(),
            exitTransition = slidingNavigationExitAnimation(),
            popEnterTransition = slidingNavigationPopEnterAnimation(),
            popExitTransition = slidingNavigationPopExitAnimation(),
        ) {
            Screens.ArtList.let { homeScreen ->
                composable(route = homeScreen.route) {
                    viewModel.onEvent(
                        uiEvent = MainViewModel.UIEvent.OnScreenLaunched(
                            screen = homeScreen
                        )
                    )

                    homeScreen.Content { artObjectNumber ->
                        navController.navigate(
                            route = Screens.ArtDetail.createRoute(
                                objectNumber = artObjectNumber
                            )
                        )
                    }
                }
            }

            val artDetailNavArgs = listOf(
                element = navArgument(name = "artObjectNumber") { type = NavType.StringType }
            )
            Screens.ArtDetail.let { artDetailScreen ->
                composable(
                    route = artDetailScreen.route,
                    arguments = artDetailNavArgs
                ) {
                    viewModel.onEvent(
                        uiEvent = MainViewModel.UIEvent.OnScreenLaunched(
                            screen = artDetailScreen
                        )
                    )

                    artDetailScreen.Content()
                }
            }
        }
    }
}
