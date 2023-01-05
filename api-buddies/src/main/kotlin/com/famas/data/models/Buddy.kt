package com.famas.data.models

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Buddy(
    val name: String,
    val note: String,
    val files: List<String>,
    val lat: Double,
    val lng: Double,
    val created_at: Int,
    @BsonId
    val id: String = ObjectId().toString()
)