package com.kisanthapa.mvvm.repository

import com.kisanthapa.mvvm.api.NewsApi
import com.kisanthapa.mvvm.db.ArticleDao

class NewsRepository(
    private val api: NewsApi,
    private val dao: ArticleDao
) {
    suspend fun getBreakingNews() = api.getBreakingNews()
}