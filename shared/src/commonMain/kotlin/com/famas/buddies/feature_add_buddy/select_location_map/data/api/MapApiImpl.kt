package com.famas.buddies.feature_add_buddy.select_location_map.data.api

import com.famas.buddies.feature_add_buddy.select_location_map.data.response.findplace.FindPlaceResponse
import com.famas.buddies.feature_add_buddy.select_location_map.data.response.getplace.PlaceResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class MapApiImpl(private val client: HttpClient) : MapApi {
    override suspend fun getPlace(lat: Double, lng: Double): PlaceResponse {
        return client.get {
            url("https://maps.googleapis.com/maps/api/geocode/json")
            parameter("latlng", "$lat,$lng")
            parameter("key", "AIzaSyDMUBcXRG08RcGle0no7luNMqiozxyrH6k")
        }.body()
    }

    override suspend fun findPlaceFromText(query: String): FindPlaceResponse {
        return client.get {
            url("https://maps.googleapis.com/maps/api/place/findplacefromtext/json")
            parameter("input", query)
            parameter("inputtype", "textquery")
            parameter("key", "AIzaSyDMUBcXRG08RcGle0no7luNMqiozxyrH6k")
            parameter("fields", "formatted_address,name,rating,opening_hours,geometry")
        }.body()
    }
}