package com.ev.newsapp.feature_news.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ev.newsapp.feature_news.data.model.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(SourceConverters::class)
abstract class ArticleDatabase: RoomDatabase() {

    abstract val dao:ArticleDao

}