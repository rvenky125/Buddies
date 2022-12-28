package com.famas.buddies.interactors.screen_add_buddy

sealed class AddBuddyEvent {
    data class SetLoading(val isLoading: Boolean): AddBuddyEvent()
    data class OnAddFile(val file: BuddyFile): AddBuddyEvent()
    data class OnRemoveFile(val file: BuddyFile): AddBuddyEvent()
    data class OnNameChange(val text: String): AddBuddyEvent()
}