package com.kisanthapa.mvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kisanthapa.mvvm.R
import com.kisanthapa.mvvm.api.NewsApi
import com.kisanthapa.mvvm.api.RetrofitInstance
import com.kisanthapa.mvvm.db.ArticleDao
import com.kisanthapa.mvvm.db.ArticleDatabase
import com.kisanthapa.mvvm.repository.NewsRepository
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {

     lateinit var viewModel: NewsViewModel
    private lateinit var newsRepository: NewsRepository
    private lateinit var articleDao: ArticleDao
    private lateinit var newsApi: NewsApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        // Init ViewModel here
        articleDao = ArticleDatabase.invoke(this).getArticleDao()
        newsApi = RetrofitInstance.api
        newsRepository = NewsRepository(dao = articleDao, api = newsApi)
        viewModel = ViewModelProvider(
            this,
            NewsViewModelFactory(newsRepository)
        ).get(NewsViewModel::class.java)

        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())
    }
}