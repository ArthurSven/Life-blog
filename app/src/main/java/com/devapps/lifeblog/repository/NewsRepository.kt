package com.devapps.lifeblog.repository

import com.devapps.lifeblog.data.remote.ArticleApi
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class NewsRepository @Inject constructor(
    private val api: ArticleApi
) {
    suspend fun getArticleList(countryCode: String, pageNumber: Int) {
        val response = try {
            api.getNews(countryCode, pageNumber)
        } catch (e: Exception) {

        }
    }

}