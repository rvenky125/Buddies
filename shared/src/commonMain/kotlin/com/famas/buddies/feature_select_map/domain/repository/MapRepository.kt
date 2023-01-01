package com.famas.buddies.feature_select_map.domain.repository

import com.famas.buddies.feature_select_map.domain.model.PlaceToShow
import com.famas.buddies.util.Response

interface MapRepository {
    suspend fun getPlace(lat: Double, lng: Double): Response<PlaceToShow>
}