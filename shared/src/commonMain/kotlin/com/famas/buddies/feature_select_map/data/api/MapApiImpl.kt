package com.famas.buddies.feature_select_map.data.api

import com.famas.buddies.feature_select_map.data.response.PlaceResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class MapApiImpl(private val client: HttpClient) : MapApi {
    override suspend fun getPlace(lat: Double, lng: Double): PlaceResponse {
        return client.get {
            url("https://maps.googleapis.com/maps/api/place/details/json?lat=$lat?lng=$lng&key=AIzaSyDMUBcXRG08RcGle0no7luNMqiozxyrH6k")
        }.body()
    }
}