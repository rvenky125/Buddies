package com.famas.buddies.util

sealed class UiEvent {
    data class ShowMessage(val message: String) : UiEvent()
    data class Navigate(val screen: Screen) : UiEvent()
    object GoBack : UiEvent()
}
