package com.famas.buddies.feature_select_map.data.api

import com.famas.buddies.feature_select_map.data.response.PlaceResponse
import com.famas.buddies.feature_select_map.domain.model.PlaceToShow

interface MapApi {
    suspend fun getPlace(lat: Double, lng: Double): PlaceResponse
}