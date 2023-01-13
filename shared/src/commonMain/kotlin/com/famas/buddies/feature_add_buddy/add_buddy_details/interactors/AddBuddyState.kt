package com.famas.buddies.feature_add_buddy.add_buddy_details.interactors

import com.famas.buddies.util.ImageFile


data class AddBuddyState(
    val loading: Boolean = false,
    val files: List<ImageFile> = emptyList(),
    val note: String = "",
    val name: String = "",
    val showMap: Boolean = false,
    val age: String = "",
    val gender: Gender? = null
)


enum class Gender(val id: Char) {
    Male('M'), Female('F'), Other('O'), Select('s')
}