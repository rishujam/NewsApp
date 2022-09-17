package com.ev.newsapp.feature_news.di

import android.app.Application
import androidx.room.Room
import com.ev.newsapp.feature_news.data.local.ArticleDatabase
import com.ev.newsapp.feature_news.data.local.SourceConverters
import com.ev.newsapp.feature_news.data.remote.NewsApi
import com.ev.newsapp.feature_news.data.repository.NewsRepository
import com.ev.newsapp.feature_news.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Provides
    @Singleton
    fun provideNewsRepository(
        api:NewsApi,
        db:ArticleDatabase
    ): NewsRepository {
        return NewsRepository(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideArticleDatabase(app:Application):ArticleDatabase {
        return Room.databaseBuilder(app, ArticleDatabase::class.java, "article_db.db")
            .build()
    }

    @Provides
    @Singleton
    fun providesNewsApi() : NewsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }


}