package com.famas.buddies.interactors.screen_add_buddy

sealed class AddBuddyEvent {
    data class SetLoading(val isLoading: Boolean): AddBuddyEvent()
    data class OnAddFiles(val files: List<BuddyFile>): AddBuddyEvent()
    data class OnRemoveFile(val index: Int): AddBuddyEvent()
    data class OnNameChange(val text: String): AddBuddyEvent()
    data class OnNoteChange(val text: String): AddBuddyEvent()

    data class OnChangeShowMap(val show: Boolean): AddBuddyEvent()
}