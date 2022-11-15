package com.ribeiroribas.worldcupqatar.ui.state

sealed class AppUiState<out T> {
    object Empty : AppUiState<Nothing>()
    object Loading : AppUiState<Nothing>()
    class Loaded<T>(val data: T) : AppUiState<T>()
    class Error(val message: String) : AppUiState<Nothing>()
}