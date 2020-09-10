package com.kisanthapa.mvvm.db

import androidx.room.TypeConverter
import com.kisanthapa.mvvm.models.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source) = source.name

    @TypeConverter
    fun toSource(name: String) = Source(name, name)
}