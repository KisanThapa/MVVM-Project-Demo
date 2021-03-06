package com.kisanthapa.mvvm.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kisanthapa.mvvm.R
import com.kisanthapa.mvvm.ui.NewsActivity
import com.kisanthapa.mvvm.ui.NewsViewModel

class ArticleFragment : Fragment(R.layout.fragment_article) {

    private lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).viewModel

    }
}