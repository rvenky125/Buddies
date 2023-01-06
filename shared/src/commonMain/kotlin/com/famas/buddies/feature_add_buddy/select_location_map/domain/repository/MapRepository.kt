package com.famas.buddies.feature_add_buddy.select_location_map.domain.repository

import com.famas.buddies.feature_add_buddy.select_location_map.data.response.findplace.Candidate
import com.famas.buddies.feature_add_buddy.select_location_map.domain.model.PlaceToShow
import com.famas.buddies.util.Response

interface MapRepository {
    suspend fun getPlace(lat: Double, lng: Double): Response<PlaceToShow>

    suspend fun findPlaceFromQuery(query: String): Response<List<Candidate>>
}