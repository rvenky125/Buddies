package com.famas.buddies.feature_add_buddy.select_location_map.data.api

import com.famas.buddies.feature_add_buddy.select_location_map.data.response.findplace.FindPlaceResponse
import com.famas.buddies.feature_add_buddy.select_location_map.data.response.getplace.PlaceResponse

interface MapApi {
    suspend fun getPlace(lat: Double, lng: Double): PlaceResponse
    suspend fun findPlaceFromText(query: String): FindPlaceResponse
}