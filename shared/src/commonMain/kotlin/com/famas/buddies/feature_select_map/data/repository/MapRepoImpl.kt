package com.famas.buddies.feature_select_map.data.repository

import com.famas.buddies.feature_select_map.data.api.MapApi
import com.famas.buddies.feature_select_map.data.response.findplace.Candidate
import com.famas.buddies.feature_select_map.domain.model.PlaceToShow
import com.famas.buddies.feature_select_map.domain.repository.MapRepository
import com.famas.buddies.util.Response

class MapRepoImpl(private val mapApi: MapApi) : MapRepository {
    override suspend fun getPlace(lat: Double, lng: Double): Response<PlaceToShow> {
        return try {
            val result = mapApi.getPlace(lat, lng)
            Response.Success(
                data = PlaceToShow(
                    name = result.results.first().formattedAddress,
                    address = result.results.first().formattedAddress
                )
            )
        } catch (e: Exception) {
            Response.Failure("Failed to fetch")
        }
    }

    override suspend fun findPlaceFromQuery(query: String): Response<List<Candidate>> {
        return try {
            val result = mapApi.findPlaceFromText(query)

            if (result.status.lowercase() == "ok") {
                Response.Success(
                    data = result.candidates
                )
            } else Response.Failure("Failed to find place")
        } catch (e: Exception) {
            Response.Failure(e.message ?: "Err")
        }
    }
}