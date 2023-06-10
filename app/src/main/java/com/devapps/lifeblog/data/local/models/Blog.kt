package com.devapps.lifeblog.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blogs")
data class Blog(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "blogId") val blogId : Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "body") val body: String,
    @ColumnInfo(name = "date_Created") val date_created: String,
    @ColumnInfo(name = "author") val author: String
)
