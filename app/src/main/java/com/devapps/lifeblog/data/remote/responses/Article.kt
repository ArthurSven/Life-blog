package com.devapps.lifeblog.data.remote.responses

data class Article(
    val articles: List<ArticleX>,
    val status: String,
    val totalResults: Int
)