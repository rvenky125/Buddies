package com.famas.buddies.feature_select_map.data.repository

import com.famas.buddies.feature_select_map.data.api.MapApi
import com.famas.buddies.feature_select_map.domain.model.PlaceToShow
import com.famas.buddies.feature_select_map.domain.repository.MapRepository
import com.famas.buddies.util.Response
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class MapRepoImpl(private val mapApi: MapApi) : MapRepository {
    override suspend fun getPlace(lat: Double, lng: Double): Response<PlaceToShow> {
        return try {
            val result = mapApi.getPlace(lat, lng)
            if (result.status == "success") {
                Response.Success(
                    data = PlaceToShow(
                        name = result.result.name,
                        address = result.result.adrAddress
                    )
                )
            } else Response.Failure("Something went wrong")
        } catch (e: Exception) {
            Response.Failure("Something went wrong")
        }
    }
}