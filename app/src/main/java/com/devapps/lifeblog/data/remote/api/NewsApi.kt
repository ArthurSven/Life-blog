package com.devapps.lifeblog.data.remote.api

import com.devapps.lifeblog.Utils.Constants.Companion.API_KEY
import com.devapps.lifeblog.Utils.Constants.Companion.BASE_URL
import com.devapps.lifeblog.data.remote.models.Article
import com.devapps.lifeblog.data.remote.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getNewsArticle(
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ) : List<Article>

}