package com.ev.newsapp.feature_news.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ev.newsapp.feature_news.data.model.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article):Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)

}