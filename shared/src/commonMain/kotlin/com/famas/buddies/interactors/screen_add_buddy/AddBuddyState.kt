package com.famas.buddies.interactors.screen_add_buddy

data class AddBuddyState(
    val loading: Boolean = false,
    val files: List<BuddyFile> = emptyList(),
    val name: String,
)

sealed class BuddyFile(
    val label: String = "",
    val uri: String = ""
) {
    class Image(
        label: String = "",
        uri: String = ""
    ): BuddyFile(label, uri)

    class Video(
        label: String = "",
        uri: String = ""
    ): BuddyFile(label, uri)
}


data class BuddyImage(
    val label: String,
    val uri: String
)