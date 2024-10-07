package com.gcyazilim.newsapp.db

import androidx.room.TypeConverter
import com.gcyazilim.newsapp.models.Source

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