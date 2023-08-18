package com.devapps.lifeblog.repository

import com.devapps.lifeblog.data.remote.api.NewsApi
import com.devapps.lifeblog.data.remote.models.Article
import com.devapps.lifeblog.data.remote.models.NewsResponse
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsApi: NewsApi
) {

    suspend fun getNewsArticles() :List<Article> {
        return newsApi.getNewsArticle()
    }
}