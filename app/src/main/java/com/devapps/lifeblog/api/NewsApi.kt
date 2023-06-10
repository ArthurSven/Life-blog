package com.devapps.lifeblog.api

import com.devapps.lifeblog.data.remote.models.NewsResponse
import com.devapps.lifeblog.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country")
    countryCode: String = "us",
    @Query("page")
    pageNumber: Int = 1,
    @Query("apiKey")
    apiKey: String = API_KEY
    ) : Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchNews(
                        @Query("q")
                        searchQuery: String,
                        @Query("page")
                        pageNumber: Int = 1,
                        @Query("apiKey")
                        apiKey: String = API_KEY
    ) : Response<NewsResponse>
}