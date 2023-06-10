package com.devapps.lifeblog.data.remote.models

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class User(
    val firstname: String,
    val lastname: String,
    val email: String,
     val username: String
)

