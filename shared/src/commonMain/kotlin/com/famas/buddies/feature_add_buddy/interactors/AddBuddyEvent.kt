package com.famas.buddies.feature_add_buddy.interactors

import com.famas.buddies.util.ImageFile

sealed class AddBuddyEvent {
    data class SetLoading(val isLoading: Boolean) : AddBuddyEvent()
    data class OnAddFiles(val files: List<ImageFile>) : AddBuddyEvent()
    data class OnRemoveFile(val index: Int) : AddBuddyEvent()
    data class OnNameChange(val text: String) : AddBuddyEvent()
    data class OnNoteChange(val text: String) : AddBuddyEvent()
    data class OnChangeShowMap(val show: Boolean) : AddBuddyEvent()
    object OnSubmit : AddBuddyEvent()
}