package com.teste

import io.ktor.server.application.*

//fun main(args: Array<String>) {
//    io.ktor.server.netty.EngineMain.main(args)
//}

import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.serialization.kotlinx.json.*
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import io.ktor.http.*
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking


fun main() {

    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            json()
        }
        initializeFirebase()
        routing {
            get("/") {
                call.respondText("Bem vindo a API, vá ate o URL: http://127.0.0.1:8080/blogs, para conseguir olhar os dados adicionados")
            }
            get("/blogs") {
                runBlocking { call.respondText(getData()) }
                //call.respondText("List of blog posts")
            }
            post("/blogs") {
                val blogPost = call.receive<BlogPost>() // Assuming BlogPost is a data class
                addBlogPost(blogPost)
                call.respondText("Blog post added", status = HttpStatusCode.Created)
            }
            put("/blogs/{id}") {
                val id = call.parameters["id"] ?: return@put call.respondText("Missing ID", status = HttpStatusCode.BadRequest)
                val updatedBlogPost = call.receive<BlogPost>()
                updateBlogPost(id, updatedBlogPost)
                call.respondText("Blog post updated")
            }
            delete("/blogs/{id}") {
                val id = call.parameters["id"] ?: return@delete call.respondText("Missing ID", status = HttpStatusCode.BadRequest)
                deleteBlogPost(id)
                call.respondText("Blog post deleted", status = HttpStatusCode.NoContent)
            }
        }
    }.start(wait=true)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
}



suspend fun getData(): String {
    val database = FirebaseDatabase.getInstance()
    val ref = database.getReference("blogs")
    val deferred = CompletableDeferred<String>()

    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            deferred.complete(snapshot.value.toString())
        }

        override fun onCancelled(error: DatabaseError) {
            deferred.completeExceptionally(error.toException())
        }
    })

    return deferred.await()
}

fun addBlogPost(blogPost: BlogPost) {
    val database = FirebaseDatabase.getInstance()
    val ref = database.getReference("blogs").push() // Create a new unique key
    ref.setValueAsync(blogPost)
}

fun updateBlogPost(id: String, updatedBlogPost: BlogPost) {
    val database = FirebaseDatabase.getInstance()
    val ref = database.getReference("blogs/$id")
    ref.setValueAsync(updatedBlogPost)
}

fun deleteBlogPost(id: String) {
    val database = FirebaseDatabase.getInstance()
    val ref = database.getReference("blogs/$id")
    ref.removeValueAsync()
}