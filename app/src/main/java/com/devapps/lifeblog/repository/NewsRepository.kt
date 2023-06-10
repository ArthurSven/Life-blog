package com.devapps.lifeblog.repository

import com.devapps.lifeblog.api.RetrofitInstance
import com.devapps.lifeblog.data.local.db.ArticleDatabase
import retrofit2.Retrofit

class NewsRepository(
    val db: ArticleDatabase) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getNews(countryCode, pageNumber)
}