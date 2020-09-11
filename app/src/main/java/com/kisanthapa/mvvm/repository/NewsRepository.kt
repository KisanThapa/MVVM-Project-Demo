package com.kisanthapa.mvvm.repository

import com.kisanthapa.mvvm.api.NewsApi
import com.kisanthapa.mvvm.db.ArticleDao
import com.kisanthapa.mvvm.models.Article

class NewsRepository(
    private val api: NewsApi,
    private val dao: ArticleDao
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        api.searchForNews(searchQuery, pageNumber)

    suspend fun upset(article: Article) = dao.upsert(article)

    fun getAllArticles() = dao.getAllArticles()

    suspend fun deleteArticle(article: Article) = dao.deleteArticle(article)
}