package com.famas.buddies.feature_add_buddy.interactors


data class AddBuddyState(
    val loading: Boolean = false,
    val files: List<BuddyFile> = emptyList(),
    val note: String = "",
    val name: String = "",
    val showMap: Boolean = false
)

sealed class BuddyFile(
    val label: String = "",
    val uri: String = ""
) {
    class Image(
        label: String = "",
        uri: String = ""
    ) : BuddyFile(label, uri)

    class Video(
        label: String = "",
        uri: String = ""
    ) : BuddyFile(label, uri)
}


data class BuddyImage(
    val label: String,
    val uri: String
)