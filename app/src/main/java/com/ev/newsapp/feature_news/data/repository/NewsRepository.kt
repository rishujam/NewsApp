package com.ev.newsapp.feature_news.data.repository

import com.ev.newsapp.feature_news.data.local.ArticleDao
import com.ev.newsapp.feature_news.data.model.Article
import com.ev.newsapp.feature_news.data.remote.NewsApi

class NewsRepository(
    private val api: NewsApi,
    private val dao: ArticleDao
) {

//    fun getBreakingNews(countryCode:String,pageNumber:Int): Flow<Resource<NewsResponse>> = flow {
//        emit(Resource.Loading())
//        try {
//            val news = api.getBreakingNews(countryCode, pageNumber)
//            if(news.isSuccessful){
//                val toReturn = NewsResponse(news.body()!!.articles, news.body()!!.status, news.body()!!.totalResults)
//                emit(Resource.Success(toReturn))
//            }else{
//                throw HttpException(news)
//            }
//        } catch (e: HttpException){
//            emit(Resource.Error(
//                message = "Something went wrong"
//            ))
//        } catch (e: IOException){
//            emit(Resource.Error(
//                message = "Couldn't reach server, check your internet connection."
//            ))
//        }
//    }
//
//    fun searchNews(searchQuery:String, pageNumber:Int):Flow<Resource<NewsResponse>> = flow {
//        emit(Resource.Loading())
//        try {
//            val news = api.searchForNews(searchQuery, pageNumber)
//            if(news.isSuccessful){
//                val body = news.body()
//                if(body!=null){
//                    val toReturn = NewsResponse(body.articles, body.status, body.totalResults)
//                    emit(Resource.Success(toReturn))
//                }else{
//                    throw HttpException(news)
//                }
//            }else{
//                throw HttpException(news)
//            }
//        } catch (e: HttpException){
//            emit(Resource.Error(
//                message = "Something went wrong"
//            ))
//        } catch (e: IOException){
//            emit(Resource.Error(
//                message = "Couldn't reach server, check your internet connection."
//            ))
//        }
//    }

    suspend fun getBreakingNews(countryCode:String,pageNumber:Int)=
        api.getBreakingNews(countryCode,pageNumber)

    suspend fun searchNews(searchQuery:String, pageNumber:Int)=
        api.searchForNews(searchQuery,pageNumber)

    suspend fun upsert(article:Article) = dao.upsert(article)

    fun getSavedNews() = dao.getAllArticles()

    suspend fun deleteArticle(article: Article) = dao.deleteArticle(article)
}