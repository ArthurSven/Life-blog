package com.devapps.lifeblog.data.local.repository

import com.devapps.lifeblog.data.db.Database
import com.devapps.lifeblog.data.local.models.Blog
import com.devapps.lifeblog.data.local.models.Daos.BlogDao
import kotlinx.coroutines.flow.Flow

class BlogRepositoryImpl(private val blogDao: BlogDao )  {

    val allBlogs: Flow<List<Blog>> = blogDao.getAllBlogs()

    suspend fun upsertBlog(blog: Blog) {
        blogDao.upsertBlog(blog)
    }

    suspend fun deleteBlog(blog: Blog) {
        blogDao.deleteBlog(blog)
    }

    suspend fun getBlog(blog: Blog) {
        blogDao.getBlog(blog.blogId)
    }


}