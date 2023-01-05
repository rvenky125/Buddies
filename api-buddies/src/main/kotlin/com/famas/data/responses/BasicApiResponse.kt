package com.famas.data.responses

@kotlinx.serialization.Serializable
data class BasicApiResponse<T>(
    val successful: Boolean,
    val message: String? = null,
    val data: T? = null
)
