package com.devapps.lifeblog.data.local.db

import androidx.room.TypeConverter
import com.devapps.lifeblog.data.remote.models.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}