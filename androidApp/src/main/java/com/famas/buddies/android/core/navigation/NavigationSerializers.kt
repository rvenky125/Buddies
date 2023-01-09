package com.famas.buddies.android.core.navigation

import com.famas.buddies.feature_add_buddy.add_buddy_details.interactors.AddBuddyNavArgs
import com.ramcosta.composedestinations.navargs.DestinationsNavTypeSerializer
import com.ramcosta.composedestinations.navargs.NavTypeSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@NavTypeSerializer
class AddBuddyTypeSerializer : DestinationsNavTypeSerializer<AddBuddyNavArgs> {
    override fun fromRouteString(routeStr: String): AddBuddyNavArgs {
        return Json.decodeFromString(routeStr)
    }

    override fun toRouteString(value: AddBuddyNavArgs): String {
        return Json.encodeToString(value)
    }
}
