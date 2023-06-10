package com.devapps.lifeblog.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.devapps.lifeblog.data.remote.models.Article

@Dao
interface ArticleDao {

    @Upsert
    suspend fun insertArticle(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<Article>

    @Delete
    suspend fun deleteArticle(article: Article)
}