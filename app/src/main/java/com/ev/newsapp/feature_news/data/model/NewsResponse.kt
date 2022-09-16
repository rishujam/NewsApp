package com.ev.newsapp.feature_news.data.model

data class NewsResponse(
        val articles: List<Article>,
        val status: String,
        val totalResults: Int
)