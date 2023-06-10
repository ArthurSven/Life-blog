package com.devapps.lifeblog.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devapps.lifeblog.data.local.models.Blog
import com.devapps.lifeblog.data.local.models.Daos.BlogDao
import com.devapps.lifeblog.data.local.models.Daos.UserDao
import com.devapps.lifeblog.data.local.models.User

@Database(entities = [Blog::class, User::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val blogDao: BlogDao

    companion object {
        const val DB_NAME = "life_blog"
    }
}