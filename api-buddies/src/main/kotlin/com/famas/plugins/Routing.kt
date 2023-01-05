package com.famas.plugins

import com.famas.data.repository.post_buddies.PostBuddyRepository
import com.famas.routes.postBuddyRoute
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val postBuddyRepository: PostBuddyRepository by inject()

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }

        postBuddyRoute(postBuddyRepository)
    }
}
