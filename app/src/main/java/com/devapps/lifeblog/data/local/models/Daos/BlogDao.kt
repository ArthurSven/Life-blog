package com.devapps.lifeblog.data.local.models.Daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.devapps.lifeblog.data.local.models.Blog
import kotlinx.coroutines.flow.Flow

@Dao
interface BlogDao {

    @Upsert
    suspend fun upsertBlog(blog: Blog)

    @Delete
    suspend fun deleteBlog(blog: Blog)

    @Query("Select * From blogs ORDER By blogId DESC")
    fun getAllBlogs(): Flow<List<Blog>>

    @Query("Select title FROM blogs WHERE blogId = :blogId")
    suspend fun getBlog(blogId: Int): String
}