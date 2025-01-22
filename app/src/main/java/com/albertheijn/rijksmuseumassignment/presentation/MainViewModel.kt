package com.albertheijn.rijksmuseumassignment.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertheijn.rijksmuseumassignment.presentation.screen.ComposableNavigationScreen
import com.albertheijn.rijksmuseumassignment.presentation.util.splashScreenDurationInMillis
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState

    init {
        showSplashScreen()
    }

    fun onEvent(uiEvent: UIEvent) {
        when (uiEvent) {
            is UIEvent.OnScreenLaunched -> _uiState.value.currentScreen.value = uiEvent.screen
        }
    }

    private fun showSplashScreen() {
        viewModelScope.launch {
            delay(timeMillis = splashScreenDurationInMillis)

            _uiState.value.shouldShowSplashScreen.value = false
        }
    }

    sealed class UIEvent {
        data class OnScreenLaunched(val screen: ComposableNavigationScreen) : UIEvent()
    }

    data class UIState(
        val shouldShowSplashScreen: MutableState<Boolean> = mutableStateOf(value = true),
        val currentScreen: MutableState<ComposableNavigationScreen?> = mutableStateOf(value = null)
    )
}