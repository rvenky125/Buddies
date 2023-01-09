package com.famas.data.models

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@kotlinx.serialization.Serializable
data class Buddy(
    val name: String,
    val note: String,
    val files: List<String>,
    val created_at: Int,
    val age: Float,
    val gender: Char,
    val lat: Double,
    val lng: Double,
    val address: String = "",
    @BsonId
    val id: String = ObjectId().toString()
)