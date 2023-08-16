package com.devapps.lifeblog.data.remote

import com.devapps.lifeblog.Utils.Constants.Companion.API_KEY
import com.devapps.lifeblog.data.remote.responses.Article
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApi {
    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ) : Response<Article>

}