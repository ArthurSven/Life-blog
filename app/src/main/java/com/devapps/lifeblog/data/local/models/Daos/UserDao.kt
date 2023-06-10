package com.devapps.lifeblog.data.local.models.Daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.devapps.lifeblog.data.local.models.Blog
import com.devapps.lifeblog.data.local.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertUser(user: User)

    @Delete
    suspend fun deleteUsr(user: User)

    @Query("SELECT * FROM users")
    fun getAllUsers() : Flow<List<User>>

    @Query("SELECT username FROM users WHERE userid = :userId")
    suspend fun getUserByName(userId: Int) : String
}