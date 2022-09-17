package com.ev.newsapp.feature_news.data.local

import androidx.room.TypeConverter
import com.ev.newsapp.feature_news.data.model.Source

class SourceConverters {

    @TypeConverter
    fun fromSource(source: Source):String{
        return source.name
    }

    @TypeConverter
    fun toSource(name:String): Source {
        return Source(name,name)
    }

}