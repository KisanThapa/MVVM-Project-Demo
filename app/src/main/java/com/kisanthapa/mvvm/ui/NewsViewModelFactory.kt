package com.kisanthapa.mvvm.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kisanthapa.mvvm.repository.NewsRepository
import java.lang.IllegalArgumentException

class NewsViewModelFactory(
    private val newsRepository: NewsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsRepository::class.java))
            return NewsViewModel(newsRepository) as T

        throw IllegalArgumentException()
    }
}