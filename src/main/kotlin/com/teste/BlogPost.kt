package com.teste

import kotlinx.serialization.Serializable

@Serializable
data class BlogPost(
    val title: String = "",
    val content: String = "",
    val author: String = "",
    val likes: Int = 0,
    val comments: Map<String, Comment> = emptyMap()
)

@Serializable
data class Comment(
    val user: String = "",
    val message: String = ""
)
