package com.kisanthapa.mvvm.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kisanthapa.mvvm.R
import com.kisanthapa.mvvm.adapters.NewsRecyclerAdapter
import com.kisanthapa.mvvm.ui.NewsActivity
import com.kisanthapa.mvvm.ui.NewsViewModel
import com.kisanthapa.mvvm.utility.Resource
import kotlinx.android.synthetic.main.fragment_breaking_news.*
import java.util.*

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    private lateinit var viewModel: NewsViewModel

    private lateinit var newsAdapter: NewsRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

        // Init Recycler Adapter
        setUpRecyclerView()

        // Observe data in view model
        viewModel.breakingNews.observe(viewLifecycleOwner, androidx.lifecycle.Observer { response ->
            when (response) {
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })


    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun setUpRecyclerView() {
        newsAdapter = NewsRecyclerAdapter()
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }
}