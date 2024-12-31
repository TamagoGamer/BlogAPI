package com.teste

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.ktor.utils.io.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlin.test.assertTrue


class ApplicationTest {

    @Test
    fun testRoot() = testApplication {
        application {
            module()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }

    @OptIn(InternalAPI::class)
    @Test
    fun testCreateBlogPost() = testApplication {
        application {
            module() // Ensure your application module is set up correctly
        }
        val newBlogPost = BlogPost(title = "Test Blog", content = "Test Content", author = "Test Author")

        // Test the POST request to create a blog post
        val response = client.post("/blogs") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(newBlogPost)
        }

        assertEquals(HttpStatusCode.Created, response.status)
        assertTrue(response.bodyAsText().contains("Blog post added with ID:"))
    }

    @OptIn(InternalAPI::class)
    @Test
    fun testUpdateBlogPost() = testApplication {
        application {
            module() // Ensure your application module is set up correctly
        }

        // First, create a blog post to update
        val newBlogPost = BlogPost(title = "Test Blog", content = "Test Content", author = "Test Author")
        val createResponse = client.post("/blogs") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(newBlogPost)
        }
        val postId = createResponse.bodyAsText().substringAfter("ID: ") // Extract the post ID from the response

        // Prepare the updated blog post
        val updatedBlogPost = BlogPost(title = "Updated Blog", content = "Updated Content", author = "Updated Author")

        // Test the PUT request to update the blog post
        val updateResponse = client.put("/blogs/$postId") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(updatedBlogPost)
        }

        assertEquals(HttpStatusCode.OK, updateResponse.status)
        assertEquals("Blog post updated", updateResponse.bodyAsText())
    }

    @OptIn(InternalAPI::class)
    @Test
    fun testDeleteBlogPost() = testApplication {
        application {
            module() // Ensure your application module is set up correctly
        }

        // First, create a blog post to delete
        val newBlogPost = BlogPost(title = "Test Blog", content = "Test Content", author = "Test Author")
        val createResponse = client.post("/blogs") {
            contentType(ContentType.Application.Json)
            body = Json.encodeToString(newBlogPost)
        }
        val postId = createResponse.bodyAsText().substringAfter("ID: ") // Extract the post ID from the response

        // Test the DELETE request to delete the blog post
        val deleteResponse = client.delete("/blogs/$postId")

        assertEquals(HttpStatusCode.NoContent, deleteResponse.status)
    }
}
