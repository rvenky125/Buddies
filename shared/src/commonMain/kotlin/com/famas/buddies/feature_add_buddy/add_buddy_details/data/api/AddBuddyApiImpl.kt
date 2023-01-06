package com.famas.buddies.feature_add_buddy.add_buddy_details.data.api

import com.famas.buddies.feature_add_buddy.add_buddy_details.data.request.PostBuddyRequest
import com.famas.buddies.util.BasicApiResponse
import com.famas.buddies.util.Constants
import com.famas.buddies.util.ImageFile
import com.famas.buddies.util.toByteArray
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.forms.*
import io.ktor.utils.io.core.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class AddBuddyApiImpl(
    private val httpClient: HttpClient
) : AddBuddyApi {
    override suspend fun addBuddy(buddy: PostBuddyRequest, files: List<ImageFile>): BasicApiResponse<Unit> {
        return httpClient.submitFormWithBinaryData(
            "${Constants.BASE_URL}buddy/add",
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