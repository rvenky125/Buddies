package com.famas.plugins

import com.famas.data.repository.get_buddies.GetBuddiesRepository
import com.famas.data.repository.post_buddies.PostBuddyRepository
import com.famas.routes.getBuddies
import com.famas.routes.postBuddyRoute
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val postBuddyRepository: PostBuddyRepository by inject()
    val getBuddyRepository: GetBuddiesRepository by inject()

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            files("pictures")

            resources("static")
        }

        postBuddyRoute(postBuddyRepository)
        getBuddies(getBuddyRepository)
    }
}
