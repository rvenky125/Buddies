    package com.famas.routes

import com.famas.data.models.Buddy
import com.famas.data.repository.post_buddies.PostBuddyRepository
import com.famas.data.requests.PostBuddyRequest
import com.famas.data.responses.BasicApiResponse
import com.famas.util.Constants
import com.famas.util.save
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.date.*
import kotlinx.datetime.Clock
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File

fun Route.postBuddyRoute(
    buddyRepo: PostBuddyRepository
) {
    post("/buddy/add") {
        val multipart = call.receiveMultipart()
        var postBuddyRequest: PostBuddyRequest? = null
        var fileNames: List<String> = emptyList()

        multipart.forEachPart { part ->
            when(part) {
                is PartData.FormItem -> {
                    if (part.name == "data") {
                        postBuddyRequest = Json.decodeFromString<PostBuddyRequest>(part.value)
                    }
                }
                is PartData.FileItem -> {
                    fileNames = fileNames.plus(part.save(Constants.PICTURES_FOLDER_PATH))
                    print(fileNames)
                }
                else -> Unit
            }
        }

        val fileUrls = fileNames.map {name ->
            "${Constants.BASE_URL}static/${Constants.PICTURES_FOLDER_NAME}/$name"
        }

        postBuddyRequest?.let { request ->
            val postBuddyAcknowledged = buddyRepo.postBuddy(
                buddy = Buddy(
                    name=request.buddy_name,
                    note = request.note,
                    files = fileUrls,
                    lat = request.lat,
                    lng = request.lng,
                    created_at = Clock.System.now().epochSeconds.toInt(),
                    age = request.age,
                    gender = request.gender
                )
            )

            if (postBuddyAcknowledged) {
                call.respond(HttpStatusCode.OK, BasicApiResponse<Unit>(successful = true))
                return@post
            } else {
                fileNames.forEach { fileName ->
                    File("${Constants.PICTURES_FOLDER_PATH}$fileName").delete()
                    call.respond(HttpStatusCode.InternalServerError)
                    return@post
                }
            }
        } ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }
    }
}