package com.albertheijn.rijksmuseumassignment.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertheijn.rijksmuseumassignment.presentation.screen.ComposableNavigationScreen
import com.albertheijn.rijksmuseumassignment.presentation.util.SPLASH_SCREEN_DURATION_IN_MILLIS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.SplashScreen)

    val uiState: StateFlow<UiState> = _uiState

    init {
        showSplashScreen()
    }

    fun onEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is UiEvent.OnScreenLaunched -> _uiState.value =
                UiState.ComposableScreen(currentScreen = uiEvent.screen)
        }
    }

    private fun showSplashScreen() {
        viewModelScope.launch {
            delay(timeMillis = SPLASH_SCREEN_DURATION_IN_MILLIS)

            _uiState.value = UiState.SplashScreen
        }
    }

    sealed class UiEvent {
        data class OnScreenLaunched(val screen: ComposableNavigationScreen) : UiEvent()
    }

    sealed class UiState {
        data class ComposableScreen(
            val currentScreen: ComposableNavigationScreen? = null
        ) : UiState()

        data object SplashScreen : UiState()
    }
}