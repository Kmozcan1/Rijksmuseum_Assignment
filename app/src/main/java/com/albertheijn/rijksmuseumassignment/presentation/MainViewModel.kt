package com.albertheijn.rijksmuseumassignment.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.albertheijn.rijksmuseumassignment.presentation.screen.ComposableNavigationScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(UIState())

    val uiState: StateFlow<UIState> = _uiState

    fun onEvent(uiEvent: UIEvent) {
        when (uiEvent) {
            is UIEvent.OnScreenLaunched -> _uiState.value.currentScreen.value = uiEvent.screen
        }
    }

    sealed class UIEvent {
        data class OnScreenLaunched(val screen: ComposableNavigationScreen) : UIEvent()
    }

    data class UIState(
        val currentScreen: MutableState<ComposableNavigationScreen?> = mutableStateOf(value = null)
    )
}