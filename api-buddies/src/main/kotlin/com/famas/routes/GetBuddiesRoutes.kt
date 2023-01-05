package com.famas.routes

import com.famas.data.models.Buddy
import com.famas.data.repository.get_buddies.GetBuddiesRepository
import com.famas.data.responses.BasicApiResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getBuddies(
    getBuddiesRepository: GetBuddiesRepository
) {
    get("/buddy/get") {
        val buddies = getBuddiesRepository.getBuddies()

        if (buddies.isNotEmpty()) {
            call.respond(HttpStatusCode.OK, BasicApiResponse(successful = true, data = buddies))
            return@get
        }

        call.respond(HttpStatusCode.NotFound, BasicApiResponse<Unit>(successful = false))
    }
}