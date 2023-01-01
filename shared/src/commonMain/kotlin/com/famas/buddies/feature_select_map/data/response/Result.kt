package com.famas.buddies.feature_select_map.data.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName("address_components")
    val addressComponents: List<AddressComponent>,
    @SerialName("adr_address")
    val adrAddress: String,
    @SerialName("business_status")
    val businessStatus: String,
    @SerialName("formatted_address")
    val formattedAddress: String,
    @SerialName("formatted_phone_number")
    val formattedPhoneNumber: String,
    @SerialName("geometry")
    val geometry: Geometry,
    @SerialName("icon")
    val icon: String,
    @SerialName("icon_background_color")
    val iconBackgroundColor: String,
    @SerialName("icon_mask_base_uri")
    val iconMaskBaseUri: String,
    @SerialName("international_phone_number")
    val internationalPhoneNumber: String,
    @SerialName("name")
    val name: String,
    @SerialName("opening_hours")
    val openingHours: OpeningHours,
    @SerialName("photos")
    val photos: List<Photo>,
    @SerialName("place_id")
    val placeId: String,
    @SerialName("plus_code")
    val plusCode: PlusCode,
    @SerialName("rating")
    val rating: Int,
    @SerialName("reference")
    val reference: String,
    @SerialName("reviews")
    val reviews: List<Review>,
    @SerialName("types")
    val types: List<String>,
    @SerialName("url")
    val url: String,
    @SerialName("user_ratings_total")
    val userRatingsTotal: Int,
    @SerialName("utc_offset")
    val utcOffset: Int,
    @SerialName("vicinity")
    val vicinity: String,
    @SerialName("website")
    val website: String
)