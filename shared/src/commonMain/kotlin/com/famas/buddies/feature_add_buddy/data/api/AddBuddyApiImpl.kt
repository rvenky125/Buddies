package com.famas.buddies.feature_add_buddy.data.api

import com.famas.buddies.feature_add_buddy.data.request.PostBuddyRequest
import com.famas.buddies.util.BasicApiResponse
import com.famas.buddies.util.ImageFile
import com.famas.buddies.util.toByteArray
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.utils.io.core.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class AddBuddyApiImpl(
    private val httpClient: HttpClient
) : AddBuddyApi {
    override suspend fun addBuddy(buddy: PostBuddyRequest, files: List<ImageFile>): BasicApiResponse<Unit> {
        return httpClient.submitFormWithBinaryData(
            "http://192.168.111.70:8080/buddy/add",
            formData =  formData {
                append("data", Json.encodeToString(buddy))
                files.forEachIndexed { index, file ->
                    append(
                        "$index",
                        "$index.jpg",
//                        contentType = ContentType.Application.,
                    ) {
                        writeFully(file.toByteArray())
                    }
                }
            }
        ).body()
    }
}